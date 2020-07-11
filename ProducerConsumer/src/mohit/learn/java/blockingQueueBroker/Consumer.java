package mohit.learn.java.blockingQueueBroker;

public class Consumer implements Runnable {

    private BlockingQueueBroker<Integer> broker;
    public Consumer(BlockingQueueBroker broker) {
        this.broker = broker;
    }
    @Override
    public void run() {
        while(true) {
            Integer nextValue = broker.take();
//            if(nextValue!=null)
            process(nextValue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void process(int value) {
        System.out.println(value);
    }
}
