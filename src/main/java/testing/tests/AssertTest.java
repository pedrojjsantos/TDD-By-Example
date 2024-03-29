package testing.tests;

import testing.Assert;
import testing.TestCase;
import testing.TestResult;

import java.util.*;

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
        final String equalsError = "%s%nExpected: %s%nActual: %s";

        Runnable oneEqualsOne = () -> Assert.assertEquals(1,1);
        Runnable oneEqualsTwoWithMessage = () -> Assert.assertEquals("Error",1,2);
        Runnable nullEqualsTwo = () -> Assert.assertEquals(null,1);

        Assert.assertNull(getErrorMsg(oneEqualsOne));


        Assert.assertEquals(
                equalsError.formatted("Error", 1, 2),
                getErrorMsg(oneEqualsTwoWithMessage));

        Assert.assertEquals(
                equalsError.formatted("", null, 1),
                getErrorMsg(nullEqualsTwo));
    }

    public void testNotEquals() {
        final String notEqualsError = "%s%nExpected not: %s";

        Runnable oneNotEqualsTwo = () -> Assert.assertNotEquals(1,2);
        Runnable oneNotEqualsOneWithMessage = () -> Assert.assertNotEquals("Error",1,1);
        Runnable nullNotEqualsNull = () -> Assert.assertNotEquals(null,null);

        Assert.assertNull(getErrorMsg(oneNotEqualsTwo));


        Assert.assertEquals(
                notEqualsError.formatted("Error", 1),
                getErrorMsg(oneNotEqualsOneWithMessage));

        Assert.assertEquals(
                notEqualsError.formatted("", null),
                getErrorMsg(nullNotEqualsNull));
    }

    public void testTrueAndFalse() {
        final String assertTrueError = "%s%nExpected: 'True'";
        final String assertFalseError = "%s%nExpected: 'False'";

        Runnable trueAssertTrue = () -> Assert.assertTrue(true);
        Runnable falseAssertTrue = () -> Assert.assertTrue("Should be true", false);
        Runnable trueAssertFalse = () -> Assert.assertFalse(true);
        Runnable falseAssertFalse = () -> Assert.assertFalse("Should be false", false);

        Assert.assertNull(getErrorMsg(trueAssertTrue));
        Assert.assertNull(getErrorMsg(falseAssertFalse));

        Assert.assertEquals( "true",
                assertTrueError.formatted("Should be true"),
                getErrorMsg(falseAssertTrue));

        Assert.assertEquals(
                assertFalseError.formatted(""),
                getErrorMsg(trueAssertFalse));
    }

    public void testFail() {
        Runnable failWithoutMsg = Assert::fail;
        Runnable failWithMsg = () -> Assert.fail("Failed");

        Assert.assertEquals("", getErrorMsg(failWithoutMsg));
        Assert.assertEquals("Failed", getErrorMsg(failWithMsg));
    }

    public void testNullAndNotNull() {
        final String assertNullError = "%s%nExpected: null%nActual: %s";
        final String assertNotNullError = "%s%nExpected not: null";

        Runnable oneAssertNull = () -> Assert.assertNull("Ops", 1);
        Runnable nullAssertNull = () -> Assert.assertNull(null);
        Runnable oneAssertNotNull = () -> Assert.assertNotNull(1);
        Runnable nullAssertNotNull = () -> Assert.assertNotNull("", null);

        Assert.assertEquals(
                assertNullError.formatted("Ops", 1),
                getErrorMsg(oneAssertNull));

        Assert.assertEquals(null, getErrorMsg(nullAssertNull));


        Assert.assertEquals(
                assertNotNullError.formatted(""),
                getErrorMsg(nullAssertNotNull));

        Assert.assertEquals(null, getErrorMsg(oneAssertNotNull));
    }

    public void testContains() {
        final String containsError = "%s%nExpected '%s' in %s";

        Integer[] integerArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> integerList = List.of(integerArray);

        Runnable containsList = () -> Assert.assertContains(integerList, 1, 2, 3, 4, 5);
        Runnable containsListErr = () -> Assert.assertContains(integerList, 1, 2, 11);
        Runnable containsArray = () -> Assert.assertContains(integerArray, 1, 2, 3, 4, 5);
        Runnable containsArrayErr = () -> Assert.assertContains(integerArray, 1, 2, 11);

        Assert.assertEquals(null, getErrorMsg(containsList));
        Assert.assertEquals(
                containsError.formatted("", 11, integerList),
                getErrorMsg(containsListErr));

        Assert.assertEquals(null, getErrorMsg(containsArray));
        Assert.assertEquals(
                containsError.formatted("", 11, List.of(integerArray)),
                getErrorMsg(containsArrayErr));

        assert throwsAssertionError(() -> Assert.assertContains((Collection<String>) null));
        assert throwsAssertionError(() -> Assert.assertContains(new ArrayList<>(), ""));
        assert !throwsAssertionError(() -> Assert.assertContains(new ArrayList<String>()));


        assert !throwsAssertionError(() -> Assert.assertContains(integerList, 2, 3, 4, 5));

        assert !throwsAssertionError(() -> Assert.assertContains(integerArray, 1, 2, 3));
        assert throwsAssertionError(() -> Assert.assertContains(integerArray, "a", "b", "c"));
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

    private String getErrorMsg(Runnable fn) {
        try {
            fn.run();
            return null;
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
