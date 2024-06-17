package playground;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


public class FieldUpdaterDemo {
    private static final AtomicIntegerFieldUpdater<FieldUpdaterDemo> UPDATER = AtomicIntegerFieldUpdater.newUpdater(FieldUpdaterDemo.class, "count");

    volatile int count = 0;

    public static void main(String[] args) throws Exception {
        final FieldUpdaterDemo demo = new FieldUpdaterDemo();
        final int threadCount = 5;
        final int numIterations = 10_000;
        final CountDownLatch doneSignal = new CountDownLatch(threadCount);
        System.out.println("CountDownLatch: " + doneSignal.getCount());

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                for (int n = 0; n < numIterations; n++) {
                    UPDATER.incrementAndGet(demo);
                }
                doneSignal.countDown();
            });
        }

        doneSignal.await();
        System.out.println("CountDownLatch await: done");
        System.out.println("CountDownLatch: " + doneSignal.getCount());
        System.out.println("End count: " + demo.count);
        executorService.shutdownNow();
    }
}
