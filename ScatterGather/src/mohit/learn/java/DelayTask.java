package mohit.learn.java;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class DelayTask implements Runnable{
    private int delay;
    private Set<String> set;
    CountDownLatch latch;
    public DelayTask(int delay, Set<String> set, CountDownLatch latch) {
        this.set  = set;
        this.delay = delay;
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread "+Thread.currentThread().getName()+" is done");
        set.add(Thread.currentThread().getName());
        if(latch!=null)
        latch.countDown();
    }
}
