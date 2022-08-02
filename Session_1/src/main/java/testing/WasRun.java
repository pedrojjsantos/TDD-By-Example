package testing;

public class WasRun extends TestCase {
    String log = "";

    WasRun(String methodName) {
        super(methodName);
    }

    public void testMethod() {
        log += "testMethod ";
    }
    public void testBrokenMethod() {
        throw new RuntimeException();
    }

    public void setUp() {
        log += "setUp ";
    }

    public void tearDown() {
        log += "tearDown ";
    }

}
