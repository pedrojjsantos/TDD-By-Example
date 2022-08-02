package testing;

public class TestCase {
    String name;

    TestCase(String name) {
        this.name = name;
    }

    public void setUp() {}
    public void tearDown() {}

    public void run(TestResult result) throws Exception {
        Class<?> klass = this.getClass();
        result.testStarted();
        this.setUp();

        try {   klass.getMethod(name).invoke(this); }
        catch (Exception th) {  result.testFailed(); }

        this.tearDown();
    }
}
