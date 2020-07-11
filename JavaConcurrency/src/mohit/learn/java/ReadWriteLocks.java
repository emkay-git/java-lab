package mohit.learn.java;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocks {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public void readResource() {
        readLock.lock();
        System.out.println("inside read resource "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            System.out.println("exiting read resource "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }

    public void writeResource() {
        writeLock.lock();
        System.out.println("inside write resource "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            System.out.println("exiting write resource "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void runReadWriteLocks() {
        Thread t1 = new Thread(() -> readResource()); t1.start();
        Thread t2 = new Thread(() -> readResource()); t2.start();

        Thread t3 = new Thread(() -> writeResource()); t3.start();
        Thread t4 = new Thread(() -> writeResource()); t4.start();
    }

}
