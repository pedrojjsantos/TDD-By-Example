package testing;

public class TestCase {
    String name;

    TestCase(String name) {
        this.name = name;
    }

    public void run() throws Exception {
        this.getClass().getMethod(name).invoke(this); // exec "self" + self.name ...
    }
}
