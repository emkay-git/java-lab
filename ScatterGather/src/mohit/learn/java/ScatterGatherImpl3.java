package mohit.learn.java;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * This approach uses completeable futures.
 */
public class ScatterGatherImpl3 {

    public static void runScatterGatherImpl3() throws InterruptedException, TimeoutException, ExecutionException {
        Set<String> set =  Collections.synchronizedSet(new HashSet<>());

//        //Void means getting no return value
//        CompletableFuture<Void> t1 = CompletableFuture.runAsync(new DelayTask(1000,set,null));
//        CompletableFuture<Void> t2 = CompletableFuture.runAsync(new DelayTask(2000,set,null));
//        CompletableFuture<Void> t3 = CompletableFuture.runAsync(new DelayTask(3000,set,null));
//
//        //combine all tasks
//        CompletableFuture<Void> allTasks = CompletableFuture.allOf(t1,t2,t3);
//
//        //if all tasks are completed within 3 seconds, fine. Else time out.
//        allTasks.get(3,TimeUnit.SECONDS);

        //Void means getting no return value
        CompletableFuture<Integer> t1 = CompletableFuture.supplyAsync(new SupplierTask(1000,set));
        CompletableFuture<Integer> t2 = CompletableFuture.supplyAsync(new SupplierTask(2000,set));
        CompletableFuture<Integer> t3 = CompletableFuture.supplyAsync(new SupplierTask(4000,set));

        //combine all tasks
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(t1,t2,t3);

//        CompletableFuture.

        //if all tasks are completed within 3 seconds, fine. Else time out.
        allTasks.get(3,TimeUnit.SECONDS);



        System.out.println("Already done");
        for(String s: set) System.out.println(s);
    }
}
