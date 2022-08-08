package testing.tests;

import testing.Assert;
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
        Assert.assertEquals("0 run, 0 failed", result.summary());

        result.testStarted();
        Assert.assertEquals("1 run, 0 failed", result.summary());

        result.testStarted();
        Assert.assertEquals("2 run, 0 failed", result.summary());
    }

    public void testSummaryWithFails() {
        result.testFailed("", null);
        Assert.assertEquals("0 run, 1 failed", result.summary());

        result.testStarted();
        Assert.assertEquals("1 run, 1 failed", result.summary());

        result.testFailed("", null);
        Assert.assertEquals("1 run, 2 failed", result.summary());
    }

    public void testListFailedTests() {
        List<TestResult.Fail> failedTests = result.listFailedTests();
        Assert.assertTrue(failedTests.isEmpty());

        result.testFailed("fail1", new AssertionError());
        result.testFailed("fail2", new NullPointerException());

        Assert.assertEquals(2, failedTests.size());

        Assert.assertEquals("fail1", failedTests.get(0).getName());
        Assert.assertEquals("AssertionError", failedTests.get(0).getErrorMsg());

        Assert.assertEquals("fail2", failedTests.get(1).getName());
        Assert.assertEquals("NullPointerException", failedTests.get(1).getErrorMsg());
    }

    public void testFailedTestsMsg() {
        String errorMsgs = result.gatherErrorMsgs();
        Assert.assertTrue(errorMsgs.isEmpty());

        result.testFailed("fail1", new AssertionError("failed"));
        result.testFailed("fail2", new NullPointerException());

        errorMsgs = result.gatherErrorMsgs();
        List<String> lines = errorMsgs.lines().toList();


        Assert.assertEquals(2, lines.size());
        Assert.assertEquals("fail1: AssertionError: \"failed\"", lines.get(0));
        Assert.assertEquals("fail2: NullPointerException", lines.get(1));
    }

    public void testResultBrokenSetUp() {
        result.setUpFailed(new NullPointerException("Ops"));
        Assert.assertEquals("setUp method failed!", result.summary());
        Assert.assertEquals("NullPointerException: \"Ops\"", result.gatherErrorMsgs());
    }
}
