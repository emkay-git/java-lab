package mohit.learn.java.blockingQueueBroker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueBroker<T> {

    private  BlockingQueue<T> blockingQueue;

    public BlockingQueueBroker(int capacity) {
        blockingQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void push(T value) {
        blockingQueue.add(value);
    }

    public T take() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }




}
