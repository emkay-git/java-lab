package mohit.learn.java.interrupt;

public class Task implements Runnable {
    @Override
    public void run() {
        while(true) {

                System.out.println(Thread.currentThread().getName());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " Have been interrupted");
                    Thread.interrupted();
                    return;
                }

        }
    }
}
