package mohit.learn.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Thread 1 gets the lock first, but then go to wait state on reaching condition.await(). This also results
 * in releasing of lock.
 * Thread 2 then gets the lock and do some work, after that it calls condition.signal(), which
 * brings the Thread1 from wait state to runnable state. And now thread 1 can work.
 *
 * Make sure Thread2 release the lock, because it it doesn't Thread1 can't reacquire the lock.
 */
public class LockCondition {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void method1() {

        lock.lock();

        try {
            System.out.println("I am acquiring the Lock");
            Thread.sleep(5000);

            condition.await();   // --> Suspending thread here.
            Thread.sleep(5000);
            System.out.println("Acquired");

        } catch(InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("I am releasing the Lock");

            Thread.sleep(5000);

            condition.signal();
            System.out.println("Released");

        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void method3() {
        lock.lock();
        try {
            System.out.println("Inside method 3 "+Thread.currentThread().getName());
            condition.await();
            System.out.println("Exiting method 3 "+Thread.currentThread().getName());

        } catch(Exception ex) {}
        finally {
            lock.unlock();
        }

    }

    public void method4() {
        lock.lock();
        try {
            Thread.sleep(5000);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void runLockCondition() {
//        Thread t1 = new Thread(() -> method1()); t1.start();
//        try {
//            Thread.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread t2 = new Thread(() -> method2()); t2.start();

        Thread t1 = new Thread(() -> method3());
        Thread t2 = new Thread(() -> method3());
        t1.start();
        t2.start();

        Thread t3 = new Thread(() -> method4());
        t3.start();

    }
}
