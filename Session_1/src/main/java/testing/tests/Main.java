package testing.tests;

import testing.TestResult;
import testing.TestSuite;

public class Main {
    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(TestCaseTest.class);

        suite.run(result);
        System.out.println(result.summary());
        System.out.print(result.description());
    }
}
