package testing.tests;

import testing.Assert;
import testing.TestCase;
import testing.TestResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AssertTest extends TestCase {
    AssertTest(String methodName) {
        super(methodName);
    }

    TestResult result;

    @Override
    public void setUp() {
        result = new TestResult();
    }

    public void testEquals() {
        assert !throwsAssertionError(() -> Assert.assertEquals(1, 1)) : "1 != 1";
        assert throwsAssertionError(() -> Assert.assertEquals(1, 2)) : "1 == 2";
        assert !throwsAssertionError(() -> Assert.assertEquals(null, null)) : "null != null";
        assert throwsAssertionError(() -> Assert.assertEquals(null, "abc")) : "null == \"abc\"";
        assert throwsAssertionError(() -> Assert.assertEquals("", null)) : "\"\" == null";
    }

    public void testNotEquals() {
        assert throwsAssertionError(() -> Assert.assertNotEquals(1, 1)) : "1 != 1";
        assert !throwsAssertionError(() -> Assert.assertNotEquals(1, 2)) : "1 == 2";
        assert throwsAssertionError(() -> Assert.assertNotEquals(null, null)) : "null != null";
        assert !throwsAssertionError(() -> Assert.assertNotEquals(null, "abc")) : "null == \"abc\"";
        assert !throwsAssertionError(() -> Assert.assertNotEquals("", null)) : "\"\" == null";
    }

    public void testTrueAndFalse() {
        assert throwsAssertionError(() -> Assert.assertTrue(false));
        assert !throwsAssertionError(() -> Assert.assertTrue(true));

        assert throwsAssertionError(() -> Assert.assertFalse(true));
        assert !throwsAssertionError(() -> Assert.assertFalse(false));
    }

    public void testFail() {
        assert throwsAssertionError(Assert::fail);
    }

    public void testNullAndNotNull() {
        assert throwsAssertionError(() -> Assert.assertNull("abc"));
        assert throwsAssertionError(() -> Assert.assertNull(1));
        assert !throwsAssertionError(() -> Assert.assertNull(null));

        assert !throwsAssertionError(() -> Assert.assertNotNull("abc"));
        assert !throwsAssertionError(() -> Assert.assertNotNull(1));
        assert throwsAssertionError(() -> Assert.assertNotNull(null));
    }

    public void testContains() {
        assert throwsAssertionError(() -> Assert.assertContains((Collection<String>) null));
        assert throwsAssertionError(() -> Assert.assertContains(new ArrayList<>(), ""));
        assert !throwsAssertionError(() -> Assert.assertContains(new ArrayList<String>()));

        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<String> strings = List.of(new String[]{"a", "ab", "abc", "bc", "c"});

        assert !throwsAssertionError(() -> Assert.assertContains(strings, "a", "c"));
        assert throwsAssertionError(() -> Assert.assertContains(strings, "a", "b", "c"));

        assert !throwsAssertionError(() -> Assert.assertContains(ints, 1, 2, 3));
        assert throwsAssertionError(() -> Assert.assertContains(ints, "a", "b", "c"));
    }

    public void testThrows() {
        final Runnable throwingNull = () -> {
            throw new NullPointerException("null");
        };
        final Runnable notThrowingRun = () -> {
        };

        assert throwsAssertionError(() -> Assert.assertThrows(AssertionError.class, throwingNull)) :
                "AssertionError == NullPointerException";
        assert !throwsAssertionError(() -> Assert.assertThrows(NullPointerException.class, throwingNull)) :
                "NullPointerException != NullPointerException";
        assert !throwsAssertionError(() -> Assert.assertThrows(null, notThrowingRun)) :
                "null != null";
    }

    public void testErrorMsg() {
        String equalsError = "\tExpected: %d%n\tActual: %d".formatted(1, 2);
        String equalsActualError = getErrorMsg(() -> Assert.assertEquals(1, 2));

        Assert.assertEquals(equalsError, equalsActualError);
    }

    private String getErrorMsg(Runnable fn) {
        try {
            fn.run();
            throw new RuntimeException("Expected to throw an AssertionError");
        } catch (AssertionError err) {
            return err.getMessage();
        }
    }

    private boolean throwsAssertionError(Runnable fn) {
        try {
            fn.run();
        } catch (Throwable error) {
            return error.getClass() == AssertionError.class;
        }
        return false;
    }
}
