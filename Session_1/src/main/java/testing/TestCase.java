package testing;

public class TestCase {
    String methodName;

    protected TestCase(String methodName) {
        this.methodName = methodName;
    }

    public void setUp() {}
    public void tearDown() {}

    public void run(TestResult result) throws Exception {
        Class<?> klass = this.getClass();
        result.testStarted();
        this.setUp();

        try {   klass.getMethod(methodName).invoke(this); }
        catch (Exception th) {  result.testFailed(); }

        this.tearDown();
    }
}
