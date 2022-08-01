package testing;

public class WasRun extends TestCase {
    boolean wasSetUp;
    boolean wasRun;

    WasRun(String methodName) {
        super(methodName);
    }

    public void testMethod() {
        wasRun = true;
    }

    public void setUp() {
        wasRun = false;
        wasSetUp = true;
    }

}
