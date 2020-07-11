package mohit.learn.java;

/**
 * In this, we learn synchronize, wait and notify
 */
public class Snynchronized {

      public void accessResource() {

        try {
            Thread.sleep(5000);
            System.out.println("Current thread "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runMultipleThreads()  {
        Thread t1 = new Thread(() -> accessResource()); t1.start();
        Thread t2 = new Thread(() -> accessResource()); t2.start();
    }

}
