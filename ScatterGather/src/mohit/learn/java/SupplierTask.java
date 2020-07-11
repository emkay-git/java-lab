package mohit.learn.java;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class SupplierTask implements Supplier<Integer> {
    private int delay;
    private Set<String> set;
    public SupplierTask(int delay, Set<String> set) {
        this.set  = set;
        this.delay = delay;
    }

    @Override
    public Integer get() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread "+Thread.currentThread().getName()+" is done");
        set.add(Thread.currentThread().getName());
        return this.delay;
    }
}
