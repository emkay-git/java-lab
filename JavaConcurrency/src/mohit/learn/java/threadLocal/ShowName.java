package mohit.learn.java.threadLocal;

public class ShowName {

    private String name;

    public ShowName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(this.name);
    }
}
