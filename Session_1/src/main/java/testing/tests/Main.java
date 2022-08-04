package testing.tests;

import testing.TestCase;
import testing.TestResult;
import testing.TestSuite;

import java.util.stream.Collectors;

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

        String description =
                result.description()
                        .lines()
                        .map(str -> '\t' + str)
                        .collect(Collectors.joining("%n"))
                        .formatted();

        System.out.printf("%s: %s%n%s%n",
                testClass.getSimpleName(), result.summary(), description);
    }
}
