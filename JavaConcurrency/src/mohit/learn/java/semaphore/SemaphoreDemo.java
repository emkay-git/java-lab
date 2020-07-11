package mohit.learn.java.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void runSemaphoreDemo() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<100;i++) {
            es.submit(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
