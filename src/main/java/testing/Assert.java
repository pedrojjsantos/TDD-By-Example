package testing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Assert {
    private static final String newline = "%n".formatted();

    private Assert() {}

    public static void assertEquals(Object expected, Object actual) {
        assertEquals("", expected, actual);
    }
    public static void assertEquals(String msg, Object expected, Object actual) {
        String treatedMsg = treatParamMessage(msg);

        if (!isEqual(expected, actual)) {
            String errorMsg = "%s%nExpected: %s%nActual: %s".formatted(
                    treatedMsg, objectToString(expected), objectToString(actual));
            throw new AssertionError(errorMsg);
        }
    }

    public static void assertNotEquals(Object expected, Object actual) {
        assertNotEquals("", expected, actual);
    }
    public static void assertNotEquals(String msg, Object expected, Object actual) {
        String treatedMsg = treatParamMessage(msg);

        if (isEqual(expected, actual)) {
            String errorMsg = "%s%nExpected not: %s".formatted(
                    treatedMsg, objectToString(actual));
            throw new AssertionError(errorMsg);
        }
    }

    private static String objectToString(Object obj) {
        if (obj == null)
            return "null";
        if (obj.getClass() == String.class)
            return "\"%s\"".formatted(obj);
        return obj.toString();
    }

    private static String treatParamMessage(String msg) {
        if (msg == null || msg.isEmpty())
            return "";
        return msg;
    }

    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("%nExpected: 'True'".formatted());
        }
    }
    public static void assertTrue(String msg, boolean condition) {
        if (!condition) {
            throw new AssertionError("%s%nExpected: 'True'".formatted(treatParamMessage(msg)));
        }
    }

    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("%nExpected: 'False'".formatted());
        }
    }
    public static void assertFalse(String msg, boolean condition) {
        if (condition) {
            throw new AssertionError("%s%nExpected: 'False'".formatted(treatParamMessage(msg)));
        }
    }

    public static void fail() {
        throw new AssertionError("");
    }
    public static void fail(String msg) {
        throw new AssertionError(msg == null ? "" : msg);
    }

    public static void assertNull(Object obj) {
        assertNull("", obj);
    }
    public static void assertNull(String msg, Object obj) {
        String treatedMsg = treatParamMessage(msg);

        if (obj != null) {
            String errorMsg = "%s%nExpected: null%nActual: %s"
                    .formatted(treatedMsg, objectToString(obj));
            throw new AssertionError(errorMsg);
        }
    }

    public static void assertNotNull(Object obj) {
        assert obj != null;
    }
    public static void assertNotNull(String msg, Object obj) {
        String treatedMsg = treatParamMessage(msg);

        if (obj == null) {
            String errorMsg = "%s%nExpected not: null".formatted(treatedMsg);
            throw new AssertionError(errorMsg);
        }
    }

    @SafeVarargs
    public static <T> void assertContains(Collection<T> collection, T ... elements) {
        if (collection == null) Assert.fail("The Collection is null");

        for (T elem : elements) {
            if (!collection.contains(elem)) {
                String errorMsg = "%s%nExpected '%s' in %s".formatted("", elem, collection);
                throw new AssertionError(errorMsg);
            }
        }
    }

    @SafeVarargs
    public static <T> void assertContains(T[] array, T ... elements) {
        if (array == null) Assert.fail("The Collection is null");

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
