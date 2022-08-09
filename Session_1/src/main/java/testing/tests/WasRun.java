package testing.tests;

import testing.Assert;
import testing.TestCase;

public class WasRun extends TestCase {
    String log = "";

    WasRun(String methodName) {
        super(methodName);
    }

    public void testMethod() {
        log += "testMethod ";
    }
    public void testBrokenMethod() {
        log += "testBrokenMethod ";
        Assert.assertEquals("asserting false", 1, 2);
    }
    public void testAnotherBrokenMethod() {
        log += "testAnotherMethod ";
        throw new NullPointerException();
    }

    public void setUp() {
        log += "setUp ";
    }

    public void tearDown() {
        log += "tearDown ";
    }

}
