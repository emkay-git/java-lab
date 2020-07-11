package mohit.learn.java;

import mohit.learn.java.blockingQueueBroker.BlockingQueueBroker;
import mohit.learn.java.blockingQueueBroker.Consumer;
import mohit.learn.java.blockingQueueBroker.Producer;
import mohit.learn.java.customBroker.CustomBroker;

import java.util.Random;

public class Main {
    
    public static void main(String[] args) throws Exception {

        runBlockinQueueBroker();
//        runCustomBroker();

    }

    public static void runBlockinQueueBroker() throws Exception {
        BlockingQueueBroker<Integer> integerBroker = new BlockingQueueBroker<>(10);
        Consumer consumer1 = new Consumer(integerBroker);
        Producer producer1 = new Producer(integerBroker);


        Thread t1 = new Thread(consumer1);
        t1.start();

        Thread.sleep(500);
        Thread t2 = new Thread(producer1);
//        t2.start();
    }

    public static void runCustomBroker() throws Exception {
        CustomBroker<Integer> customIntegerBroker = new CustomBroker<>(10);

        final Runnable consumer = () -> {
            while(true) {
                try {
                    System.out.println("Value taken by "+Thread.currentThread().getName()+" "+customIntegerBroker.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();

        final Runnable producer = () -> {
            while(true) {
                try {
                    int value = new Random().nextInt(10);
                    System.out.println("Value pushed "+value);
                    customIntegerBroker.push(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producer).start();
    }
}
