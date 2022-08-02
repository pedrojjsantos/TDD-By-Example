package testing;

public class TestResult {
    int runCount = 0;

    public void testStarted() {
        runCount++;
    }

    public String summary() {
        return "%d run, 0 failed".formatted(runCount);
    }
}
