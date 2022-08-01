package testing;

public class TestCase {
    String name;

    TestCase(String name) {
        this.name = name;
    }

    public void setUp() {}
    public void tearDown() {}

    public void run() throws Exception {
        Class<?> klass = this.getClass();

        this.setUp();
        klass.getMethod(name).invoke(this);
        this.tearDown();
    }
}
