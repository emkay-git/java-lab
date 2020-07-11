package mohit.learn.java;

import mohit.learn.java.interrupt.InterruptDemo;
import mohit.learn.java.semaphore.SemaphoreDemo;
import mohit.learn.java.threadLocal.ThreadLocalDemo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args)  {
        SemaphoreDemo sd = new SemaphoreDemo();
        sd.runSemaphoreDemo();
//        try {
//            InterruptDemo.run();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        JavaSynchronize javaSync = new JavaSynchronize();
//        javaSync.runMultipleThreads();
//        Locks locks = new Locks();
//        locks.runLocks();
//          LockCondition lockCondition = new LockCondition();
//          lockCondition.runLockCondition();

//            ReadWriteLocks locks = new ReadWriteLocks();
//            locks.runReadWriteLocks();

//        ThreadLocalDemo.run();
	// write your code here

//        runNThread(2);
//        runFixedThreadPool(4,100);
//        runCacheableThreadPool(100);
//        runScheduledPool(10);
//        executorServiceRejectHandler();
//            callableAndFutures();
    }

    public static void callableAndFutures() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> value = service.submit(new CallableTask(10));
        try {
            System.out.println(value.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void executorServiceRejectHandler() {
            ExecutorService service = new ThreadPoolExecutor(3,3,1,TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(3)
                    );
            for(int i=0;i<10;i++) {
                try {
                    if(i>5) {
                      List<Runnable> remainingTasks =  service.shutdownNow();
                        for (Runnable remainingTask : remainingTasks) {
                            remainingTask.run();
                        }
                    break;
                    }
                    service.execute(new Task(i));
                }
                catch(RejectedExecutionException execption) {
                    execption.printStackTrace();

                }
            }
    }

    public static void runScheduledPool(int poolCount) {
        System.out.println(LocalTime.now());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

//        for(int i=0;i<5&&false;i++) {
//            service.schedule(new Task(i),10, TimeUnit.SECONDS);
//        }

//        service.scheduleAtFixedRate(new Task(1),3,5,TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new Task(1),3,3,TimeUnit.SECONDS);
    }

    public static void runFixedThreadPool(int poolCount, int tasksCount) {
        ExecutorService service = Executors.newFixedThreadPool(poolCount);

        for(int i=0;i<tasksCount;i++) {
            service.execute(new Task(i));
        }
    }

    public static void runCacheableThreadPool(int tasksCount) {
        ExecutorService service = Executors.newCachedThreadPool(); // it can take keepAlive time as an arguement
        for(int i=0;i<tasksCount;i++) {
            service.execute(new Task(i));
        }
    }
    
    public static void runNThread(int count) {
        // how execute a task using thread
        // main thread creates a new thread thread-0
        for(int i=0;i<count;i++) {
            Thread thread = new Thread(new Task(i));
            thread.start();
        }

    }

    public static void learnExecutorService()  {
        // Executor service is a thread-pool framework for java.
    }

    static class Task implements Runnable {
        int index;
        public Task(int index) {
            this.index = index;
        }
        public void run() {
            System.out.println("Thread name "+ Thread.currentThread().getName()+ " Index value = "+index);
        }
    }

    static class TaskWithSleep implements Runnable {
        int index;

        TaskWithSleep(int index) {
            this.index = index;
        }
        public void run()  {
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread name "+ Thread.currentThread().getName()+" "+index);
        }
    }

    static class RejectionHandler implements  RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            r.run();
        }

    }

    static class CallableTask implements  Callable<Integer> {
        int index;
        CallableTask(int a) {
            this.index = a;
        }
        public Integer call() {
           return index+10;
        }
    }
}
