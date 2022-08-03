package testing;

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
        result.testFailed("", null);
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
        assert "3 run, 2 failed".equals(result.summary()) : result.summary();
    }

    public void testResultListFailedTests() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);

        List<TestResult.Fail> failedTests = result.listFailedTests();

        assert failedTests.size() == 2;
        assert failedTests.get(0).getName().equals("testBrokenMethod");
        assert failedTests.get(1).getName().equals("testAnotherBrokenMethod");
    }

    public void testResultFailedTestDescription() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);

        String description = result.description();
        List<String> lines = description.lines().toList();

        assert lines.size() == 2;
        assert lines.get(0).equals("testBrokenMethod: AssertionError: \"asserting false\"");
        assert lines.get(1).equals("testAnotherBrokenMethod: NullPointerException");

    }

    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(TestCaseTest.class);

        suite.run(result);
        System.out.println(result.summary());
        System.out.print(result.description());

    }
}