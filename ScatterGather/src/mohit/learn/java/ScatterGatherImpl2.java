package mohit.learn.java;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This approach uses a countdown latch.
 * How countdown works is, it's actually a counter for the runnable tasks,
 * If we have set the countdown of 3 for example and passed it to our runnable task
 * they decrease it, and once it is 0, we can say job completed. It has timeout
 * which ensures job is completed in a given time.
 */
public class ScatterGatherImpl2 {
    private static ExecutorService es;

    public static void runScatterGatherImpl2() throws InterruptedException {
        Set<String> set =  Collections.synchronizedSet(new HashSet<>());
        es = Executors.newFixedThreadPool(4);

        CountDownLatch latch = new CountDownLatch(3);

        es.submit(new DelayTask(1000,set,latch));
        es.submit(new DelayTask(1500,set,latch));
        es.submit(new DelayTask(3000,set,latch));

        latch.await(3, TimeUnit.SECONDS);


        System.out.println("Already done");
        for(String s: set) System.out.println(s);
    }
}
