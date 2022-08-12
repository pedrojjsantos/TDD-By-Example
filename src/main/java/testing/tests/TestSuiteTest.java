package testing.tests;

import testing.Assert;
import testing.TestCase;
import testing.TestResult;
import testing.TestSuite;

public class TestSuiteTest extends TestCase {
    TestSuiteTest(String methodName) {
        super(methodName);
    }

    TestResult result;

    @Override
    public void setUp() {
        result = new TestResult();
    }

    public void testAddTestMethod() throws Exception {
        TestSuite suite = new TestSuite();
        suite.add(new WasRun("testMethod"));
        suite.add(new WasRun("testBrokenMethod"));
        suite.add(new WasRun("testAnotherBrokenMethod"));
        suite.run(result);

        Assert.assertEquals("3 run, 2 failed", result.summary());
    }

    public void testCreateSuiteFromClass() throws Exception {
        TestSuite suite = new TestSuite(WasRun.class);
        suite.run(result);
        Assert.assertEquals("3 run, 2 failed", result.summary());
    }

    public void testAddSuite() throws Exception {
        TestSuite innerSuit = new TestSuite(WasRun.class);
        TestSuite suite = new TestSuite();
        suite.add(innerSuit);
        suite.run(result);

        Assert.assertEquals("3 run, 2 failed", result.summary());
    }
}
