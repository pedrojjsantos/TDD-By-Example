package testing.tests;

import testing.TestCase;
import testing.TestResult;
import testing.TestSuite;

import static testing.Assert.*;

public class FibonacciTest extends TestCase {
    FibonacciTest(String methodName) {
        super(methodName);
    }

    public void testFibonacci() {
        int[][] cases = {
                {0,0},
                {1,1},
                {2,1},
                {3,2},
                {4,3}
        };

        for (int[] c : cases)
            assertEquals(c[1], fib(c[0]));
    }

    private int fib(int n) {
        if (n < 2) return n;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(FibonacciTest.class);

        suite.run(result);
        System.out.println(result.summary());
        for (String desc : result.getFailDescriptions())
            System.out.println(desc);
    }
}
