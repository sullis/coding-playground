package playground;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class AtomicCounterWithLimit {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final int limit;

    public AtomicCounterWithLimit(int limit) {
        this.limit = limit;
    }

    public boolean increment() {
        if (counter.get() == this.limit) {
            return false;
        }
        counter.incrementAndGet();
        return true;
    }

    public int count() {
        return this.counter.get();
    }

    public static void main(String[] args) throws Exception {
        final AtomicCounterWithLimit counter = new AtomicCounterWithLimit(5);
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new Thread(runnable));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("counter count=" + counter.count());
    }
}
