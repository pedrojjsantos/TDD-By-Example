package testing;

public class TestCaseTest extends TestCase {
    TestCaseTest(String name) {
        super(name);
    }

    public void testTemplateMethod() throws Exception {
        WasRun test = new WasRun("testMethod");
        test.run();
        assert test.log.equals("setUp testMethod tearDown ");
    }

    public void testResult() throws Exception {
        WasRun test = new WasRun("testMethod");
        TestResult result = test.run();
        assert "1 run, 0 failed".equals(result.summary()) : result.summary();
    }

    public void testFailedResult() throws Exception {
        WasRun test = new WasRun("testBrokenMethod");
        TestResult result = test.run();
        assert "1 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public void testFailedResultFormatting() throws Exception {
        TestResult result = new TestResult();
        result.testStarted();
        result.testFailed();
        assert "1 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public static void main(String[] args) throws Exception {
        new TestCaseTest("testTemplateMethod").run();
        new TestCaseTest("testResult").run();
        new TestCaseTest("testFailedResult").run();
        new TestCaseTest("testFailedResultFormatting").run();
    }
}