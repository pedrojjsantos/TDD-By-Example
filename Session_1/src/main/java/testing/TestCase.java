package testing;

public class TestCase {
    String name;

    TestCase(String name) {
        this.name = name;
    }

    public void setUp() {}
    public void tearDown() {}

    public TestResult run() throws Exception {
        Class<?> klass = this.getClass();

        TestResult result = new TestResult();
        result.testStarted();

        this.setUp();
        klass.getMethod(name).invoke(this);
        this.tearDown();

        return result;
    }
}
