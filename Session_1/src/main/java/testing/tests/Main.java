package testing.tests;

import testing.TestResult;
import testing.TestSuite;

public class Main {
    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite();

        suite.add(new TestSuite(TestCaseTest.class));
        suite.add(new TestSuite(TestResultTest.class));
        suite.add(new TestSuite(TestSuiteTest.class));
        suite.add(new TestSuite(AssertTest.class));

        suite.run(result);
        System.out.println(result.summary());

        for (String description : result.getFailDescriptions())
            System.out.println(description);
    }
}

// pass: \u2714
// fail: \u2716