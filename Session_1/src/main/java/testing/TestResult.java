package testing;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    static final String ERROR_DESCRIPTION_HEADER = "%s: %s%n";
    Fail failedSetUp = null;
    List<Fail> failedTests = new ArrayList<>();
    int runCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed(String methodName, Throwable error) {
        failedTests.add(new Fail(methodName, error));
    }

    public String summary() {
        if (failedSetUp != null) return "setUp method failed!";
        return "%d run, %d failed".formatted(runCount, failedTests.size());
    }

    public List<Fail> listFailedTests() {
        return failedTests;
    }

    public String description() {
        StringBuilder builder = new StringBuilder();
        if (failedSetUp != null)
            builder.append(failedSetUp.getErrorMsg());
        else
            for (Fail test : failedTests) {
                String name = test.getName();
                String msg = test.getErrorMsg();

                builder.append(ERROR_DESCRIPTION_HEADER.formatted(name, msg));
            }
        return builder.toString();
    }

    public void setUpFailed(Throwable error) {
        failedSetUp = new Fail("setUp", error);
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
            if (error == null) return "";
            String errorClass = error.getClass().getSimpleName();
            if (error.getMessage() == null)
                return errorClass;
            return errorClass + ": \"" + error.getMessage() + '"';
        }
    }
}
