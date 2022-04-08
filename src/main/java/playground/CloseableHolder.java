package playground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Objects.requireNonNull;

public final class CloseableHolder<T extends Closeable> implements Closeable {
    private static Logger LOGGER = LoggerFactory.getLogger(CloseableHolder.class);

    // This Runnable will be invoked when all items that were added to the holder are closed from within the
    // close() method.
    protected final Runnable onClose;
    protected final Collection<Closeable> closeables;
    private State state;

    private enum State {
        OPEN, CLOSING, CLOSED
    }

    public CloseableHolder(final Runnable onClose) {
        this.onClose = onClose;
        this.closeables = new ArrayList<>();
        this.state = State.OPEN;
    }

    // Add the passed item to this holder. If the holder is closed already, close the passed item.
    // This method can be called concurrently from many threads.
    public synchronized void add(final T toAdd) throws IOException {
        if (this.state != State.OPEN) {
            toAdd.close();
        } else {
            this.closeables.add(toAdd);
        }
    }

    // Remove the passed item from this holder, if it exists. This method must NOT close the removed item.
    // This method can be called concurrently from many threads.
    public synchronized void remove(T toRemove) {
        this.closeables.remove(toRemove);
    }

    // close all items that were added but not removed when this method is invoked.
    // This method can be called concurrently from many threads but only one thread should initiate closure.
    @Override
    public synchronized void close() throws IOException {
        if (state == State.OPEN) {
            state = State.CLOSING;
            try {
                for (Closeable closable: closeables) {
                    try {
                        closable.close();
                    } catch (Exception ex) {
                        LOGGER.warn("unable to close", ex);
                    }
                }
            } finally {
                this.onClose.run();
                this.state = State.CLOSED;
            }
        }
    }
}

class CloseableHolderApp {
    public static void main(final String[] args) throws Exception {
        Runnable runnable = () -> System.out.println("run() called");
        CloseableHolder holder = new CloseableHolder(runnable);
        holder.close();
    }
}
