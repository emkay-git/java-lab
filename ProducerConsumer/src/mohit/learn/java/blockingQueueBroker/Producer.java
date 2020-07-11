package mohit.learn.java.blockingQueueBroker;

import java.util.Random;

public class Producer implements Runnable {

    BlockingQueueBroker broker;

    public Producer(BlockingQueueBroker broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(true) {
            int nextValue = r.nextInt(20);
            System.out.println("Pusing value "+nextValue);
            broker.push(nextValue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
