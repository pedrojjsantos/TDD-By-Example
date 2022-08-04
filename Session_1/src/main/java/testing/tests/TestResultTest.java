package testing.tests;

import testing.TestCase;
import testing.TestResult;

import java.util.List;

public class TestResultTest extends TestCase {
    TestResultTest(String methodName) {
        super(methodName);
    }

    TestResult result;

    @Override
    public void setUp() {
        result = new TestResult();
    }

    public void testSummaryWithNoFails() {
        assert result.summary().equals("0 run, 0 failed");

        result.testStarted();
        assert result.summary().equals("1 run, 0 failed");

        result.testStarted();
        assert result.summary().equals("2 run, 0 failed");
    }

    public void testSummaryWithFails() {
        result.testFailed("", null);
        assert result.summary().equals("0 run, 1 failed");

        result.testStarted();
        assert result.summary().equals("1 run, 1 failed");

        result.testFailed("", null);
        assert result.summary().equals("1 run, 2 failed");
    }

    public void testListFailedTests() {
        List<TestResult.Fail> failedTests = result.listFailedTests();
        assert failedTests.size() == 0;

        result.testFailed("fail1", new AssertionError());
        result.testFailed("fail2", new NullPointerException());

        assert failedTests.size() == 2;

        assert failedTests.get(0).getName().equals("fail1");
        assert failedTests.get(0).getErrorMsg().equals("AssertionError");

        assert failedTests.get(1).getName().equals("fail2");
        assert failedTests.get(1).getErrorMsg().equals("NullPointerException");
    }

    public void testFailedTestsDescription() throws Exception {
        String description = result.description();
        assert description.isEmpty();

        result.testFailed("fail1", new AssertionError("failed"));
        result.testFailed("fail2", new NullPointerException());

        description = result.description();
        List<String> lines = description.lines().toList();


        assert lines.size() == 2;
        assert lines.get(0).equals("fail1: AssertionError: \"failed\"");
        assert lines.get(1).equals("fail2: NullPointerException");
    }

    public void testResultBrokenSetUp() {
        result.setUpFailed(new NullPointerException("Ops"));
        assert result.summary().equals("setUp method failed!") : result.summary();
        assert result.description().equals("NullPointerException: \"Ops\"") : result.description();
    }
}
