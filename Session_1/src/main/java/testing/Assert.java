package testing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Assert {
    private Assert() {}

    public static void assertEquals(Object expected, Object actual) {
        assert isEqual(expected, actual);
    }

    public static void assertNotEquals(Object expected, Object actual) {
        assert !isEqual(expected, actual);
    }

    public static void assertTrue(boolean condition) {
        assert condition;
    }

    public static void assertFalse(boolean condition) {
        assert !condition;
    }

    public static void fail() {
        assert false;
    }

    public static void assertNull(Object obj) {
        assert obj == null;
    }

    public static void assertNotNull(Object obj) {
        assert obj != null;
    }

    @SafeVarargs
    public static <T> void assertContains(Collection<T> collection, T ... elements) {
        if (collection == null) Assert.fail();

        Assert.assertTrue(collection.containsAll(List.of(elements)));
    }

    @SafeVarargs
    public static <T> void assertContains(T[] array, T ... elements) {
        if (array == null) Assert.fail();

        assertContains(Arrays.asList(array), elements);
    }

    private static boolean isEqual(Object expected, Object actual) {
        if (expected == null)
            return actual == null;
        else return expected.equals(actual);
    }

    public static void assertThrows(Class<? extends Throwable> expectedThrowable, Runnable throwingRunnable) {
        try {
            throwingRunnable.run();
            if (expectedThrowable != null)
                fail();
        } catch (Throwable error) {
            assert expectedThrowable == error.getClass();
        }
    }
}
