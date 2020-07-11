package mohit.learn.java.threadLocal;

public class ThreadSafeShowName {
    public  static ThreadLocal<ShowName> threadSafeName = new ThreadLocal<>(){
            @Override
            protected ShowName initialValue() {
                System.out.println(Thread.currentThread().getName());
                return new ShowName("");
            }

            @Override
            public ShowName get() {
                return super.get();
            }


    };


}
