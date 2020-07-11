package mohit.learn.java;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Using Thread.sleep() to implement
 * Problem with this approach,
 * In case all the three tasks are completed before a timeout, main thread still continues to waits for
 * it till Thread.sleep() is completed. So it's slow.
 */
public class ScatterGatherImpl1 {
    private static ExecutorService es;

    public static void runScatterGatherImpl1() throws InterruptedException {
        Set<String> set =  Collections.synchronizedSet(new HashSet<>());
        es = Executors.newFixedThreadPool(4);
        es.submit(new DelayTask(1000,set,null));
        es.submit(new DelayTask(1500,set,null));
        es.submit(new DelayTask(4000,set,null));

        Thread.sleep(3*1000);
        System.out.println("Already done");
        for(String s: set) System.out.println(s);
    }
}
