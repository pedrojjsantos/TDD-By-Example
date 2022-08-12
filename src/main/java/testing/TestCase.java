package testing;

import java.lang.reflect.InvocationTargetException;

public class TestCase implements Test{
    String methodName;

    protected TestCase(String methodName) {
        this.methodName = methodName;
    }

    public void setUp() {}
    public void tearDown() {}

    public void run(TestResult result) throws Exception {
        Class<?> klass = this.getClass();
        try {
            this.setUp();
        } catch (Exception e) {
            result.setUpFailed(e);
            this.tearDown();
            return;
        }

        result.testStarted();

        try {   klass.getMethod(methodName).invoke(this); }
        catch (InvocationTargetException e) {
            result.testFailed(methodName, e.getCause());
        } finally {
            this.tearDown();
        }

    }
}
