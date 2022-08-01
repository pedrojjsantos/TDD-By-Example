package testing;

public class WasRun extends TestCase {
    int numberOfTestsRan = 0; //self.wasRun = None

    WasRun(String methodName) {
        super(methodName);
    }

    public void testMethod() {
        numberOfTestsRan = 1;
    }
}
