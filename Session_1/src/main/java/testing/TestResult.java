package testing;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    static final String ERROR_DESCRIPTION_HEADER = "%s: %s%n";
    List<Fail> failedTests = new ArrayList<>();
    int runCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed(String methodName, Throwable error) {
        failedTests.add(new Fail(methodName, error));
    }

    public String summary() {
        return "%d run, %d failed".formatted(runCount, failedTests.size());
    }

    public List<Fail> listFailedTests() {
        return failedTests;
    }

    public String description() {
        StringBuilder builder = new StringBuilder();
        for (Fail test : failedTests) {
            String name = test.getName();
            String msg = test.getErrorMsg();

            builder.append(ERROR_DESCRIPTION_HEADER.formatted(name, msg));
        }
        return builder.toString();
    }

    static class Fail {
        String name;
        Throwable error;

        Fail(String testName, Throwable error) {
            this.name = testName;
            this.error = error;
        }

        public String getName() {
            return name;
        }

        public String getErrorMsg() {
            String errorClass = error.getClass().getSimpleName();
            if (error.getMessage() == null)
                return errorClass;
            return errorClass + ": \"" + error.getMessage() + '"';
        }
    }
}
