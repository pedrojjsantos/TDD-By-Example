package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestResult {
    static final String ERROR_DESCRIPTION_HEADER = "%s: %s%s";
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

    public List<String> getFailDescriptions() {
        List<String> descriptionList = new ArrayList<>();
        if (failedSetUp != null) {
            descriptionList.add(failedSetUp.getDescriptionHeader());
        }
        else {
            for (Fail test : failedTests) {
                String name = test.getName();
                String msg = test.getDescriptionHeader();
                String description = test.getDescription();

                descriptionList.add(ERROR_DESCRIPTION_HEADER.formatted(name, msg, description));
            }
        }
        return descriptionList;
    }

    public void setUpFailed(Throwable error) {
        failedSetUp = new Fail("setUp", error);
    }

    public static class Fail {
        String name;
        Throwable error;
        String header = "";
        String description = "";

        Fail(String testName, Throwable error) {
            this.name = testName;
            this.error = error;
            setMessages();
        }

        public String getName() {
            return name;
        }

        public String getDescriptionHeader() {
            return header;
        }

        public String getDescription() {
            return description;
        }

        private void setMessages() {
            if (error == null) return;

            String errorClass = error.getClass().getSimpleName();

            if (error.getMessage() == null || error.getMessage().isEmpty()) {
                header = errorClass;
                return;
            }

            List<String> lines = error.getMessage().lines().toList();
            header = "%s: %s".formatted(errorClass, lines.get(0));

            if (lines.size() > 1) {
                String newLine = "%n".formatted();
                description = newLine + lines.stream()
                        .skip(1)
                        .map(str -> '\t' + str)
                        .collect(Collectors.joining(newLine));
            }
        }
    }
}
