package mohit.learn.java.interrupt;

public class InterruptDemo {

    public static void run() throws InterruptedException {
//        System.out.println("Hello");
        Thread another = new Thread(new Task());
        another.start();

        Thread.sleep(100);
//
        another.interrupt();
    }
}
