package testing;

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
        assert test.log.equals("setUp testMethod tearDown ");
    }

    public void testResult() throws Exception {
        WasRun test = new WasRun("testMethod");
        test.run(result);
        assert "1 run, 0 failed".equals(result.summary()) : result.summary();
    }

    public void testFailedResult() throws Exception {
        WasRun test = new WasRun("testBrokenMethod");
        test.run(result);
        assert "1 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public void testFailedResultFormatting() throws Exception {
        result.testStarted();
        result.testFailed();
        assert "1 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public void testSuite() throws Exception {
        TestSuite suite = new TestSuite();
        suite.add(new WasRun("testMethod"));
        suite.add(new WasRun("testBrokenMethod"));
        suite.run(result);

        assert "2 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public void testCreateSuiteFromClass() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);
        assert "2 run, 1 failed".equals(result.summary()) : result.summary();
    }

    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(TestCaseTest.class);

        suite.run(result);
        System.out.println(result.summary());
    }
}