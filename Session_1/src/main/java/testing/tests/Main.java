package testing.tests;

import testing.TestCase;
import testing.TestResult;
import testing.TestSuite;

public class Main {
    public static void main(String[] args) throws Exception {
        runTestClass(TestCaseTest.class);
        runTestClass(TestResultTest.class);
        runTestClass(TestSuiteTest.class);
    }

    private static void runTestClass(Class<? extends TestCase> testClass) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(testClass);

        suite.run(result);

        System.out.printf("%s: %s%n%s%n",
                testClass.getSimpleName(), result.summary(), result.description());
    }
}
