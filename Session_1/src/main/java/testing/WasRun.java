package testing;

public class WasRun {
    int numberOfTestsRan = 0;

    WasRun(String methodName) {}

    public void testMethod() {
        numberOfTestsRan = 1;
    }
}
