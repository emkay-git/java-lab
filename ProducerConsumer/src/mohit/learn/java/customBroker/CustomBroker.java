package mohit.learn.java.customBroker;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBroker<T> {
    ReentrantLock lock = new ReentrantLock();
    Condition emptyCondition = lock.newCondition();
    Condition fullCondition = lock.newCondition();
    private int maxSize;
    private Queue<T> queue;

    public CustomBroker(int capacity) {
        this.maxSize = capacity;
        queue = new LinkedList<>();
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==0)  emptyCondition.await();

            T value = queue.poll();
            fullCondition.signalAll();
            return value;
        } finally {
            lock.unlock();
        }

    }

    public void push(T value) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==maxSize) fullCondition.await();
            queue.add(value);
            Thread.sleep(1000);
            emptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
