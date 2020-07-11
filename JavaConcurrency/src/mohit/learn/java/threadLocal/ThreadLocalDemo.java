package mohit.learn.java.threadLocal;

public class ThreadLocalDemo {

    public static void run() {
        Thread t1 = new Thread(() -> {
          ThreadSafeShowName.threadSafeName.set(new ShowName("Mohit"));
          ShowName name = ThreadSafeShowName.threadSafeName.get();
          name.show();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            ThreadSafeShowName.threadSafeName.get().show();
            System.out.println("Called too");
        });
        t2.start();

    }
}
