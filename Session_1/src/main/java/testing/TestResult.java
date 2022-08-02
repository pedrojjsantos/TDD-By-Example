package testing;

public class TestResult {
    int runCount = 0;
    int failedCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed() {
        failedCount++;
    }

    public String summary() {
        return "%d run, %d failed".formatted(runCount, failedCount);
    }
}
