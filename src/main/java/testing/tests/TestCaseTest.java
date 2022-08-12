package testing.tests;

import testing.*;

import java.util.List;

public class TestCaseTest extends TestCase {
    TestResult result;

    TestCaseTest(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        this.result = new TestResult();
    }

    public void testTemplateMethod() throws Exception {
        WasRun test = new WasRun("testMethod");
        test.run(result);
        Assert.assertEquals("setUp testMethod tearDown ", test.log);
    }

    public void testResult() throws Exception {
        WasRun test = new WasRun("testMethod");
        test.run(result);
        Assert.assertEquals("1 run, 0 failed", result.summary());
    }

    public void testFailedResult() throws Exception {
        WasRun test = new WasRun("testBrokenMethod");
        test.run(result);
        Assert.assertEquals("1 run, 1 failed", result.summary());
    }

    public void testResultListFailedTests() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);

        List<TestResult.Fail> failedTests = result.listFailedTests();

        Assert.assertEquals(2, failedTests.size());
        Assert.assertEquals("testBrokenMethod", failedTests.get(0).getName());
        Assert.assertEquals("testAnotherBrokenMethod", failedTests.get(1).getName());
    }

    public void testResultFailedTestErrorMessages() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);

        List<String> errorMsgs = result.getFailDescriptions();
        Assert.assertEquals(2, errorMsgs.size());
        Assert.assertEquals("testAnotherBrokenMethod: NullPointerException", errorMsgs.get(1));

        List<String> lines = errorMsgs.get(0).lines().toList();
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals(
                "testBrokenMethod: AssertionError: asserting false", lines.get(0));
        Assert.assertEquals("\tExpected: 1", lines.get(1));
        Assert.assertEquals("\tActual: 2", lines.get(2));
    }

    public void testBrokenSetUp() throws Exception {
        BrokenWasRun test = new BrokenWasRun("testMethod");
        test.run(result);

        Assert.assertEquals("setUp tearDown ", test.log);
        Assert.assertEquals("setUp method failed!", result.summary());
    }

    public void testRunTearDownAfterTheTestFails() throws Exception {
        WasRun test = new WasRun("testBrokenMethod");
        test.run(result);

        Assert.assertEquals("1 run, 1 failed", result.summary());
        Assert.assertEquals("setUp testBrokenMethod tearDown ", test.log);
    }
}