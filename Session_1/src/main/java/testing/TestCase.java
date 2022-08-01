package testing;

public class TestCase {
    String name;

    TestCase(String name) {
        this.name = name;
    }

    public void setUp() {}

    public void run() throws Exception {
        Class<?> klass = this.getClass();

        klass.getMethod("setUp").invoke(this);
        klass.getMethod(name).invoke(this); // exec "self" + self.name ...
    }
}
