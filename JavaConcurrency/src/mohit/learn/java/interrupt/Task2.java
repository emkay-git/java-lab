package mohit.learn.java.interrupt;

import java.util.concurrent.Callable;

public class Task2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
    System.out.println("Started");
        while(true) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            if(Thread.interrupted()) {
                System.out.println("Interrupted");

                throw new InterruptedException();
            }
        }
    }
}
