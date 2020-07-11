package mohit.learn.java;

import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    //making it true or false decide fairness of lock.
    ReentrantLock lock = new ReentrantLock(true);
    public void sharedResource() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }


    }

    public void runLocks() {
        for(int i=0;i<10;i++) {
            Thread t1 = new Thread(() -> sharedResource());
            t1.start();
        }

//        Thread t2 = new Thread(() -> sharedResource());
//        t1.start();
//        t2.start();
    }
}
