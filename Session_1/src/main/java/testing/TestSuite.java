package testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite {
    List<TestCase> tests = new ArrayList<>();
    Class<? extends TestCase> testClass = null;

    public TestSuite() {}

    public TestSuite(Class<? extends TestCase> testClass) throws Exception {
        this.testClass = testClass;
        List<TestCase> testCases = Arrays.stream(testClass.getMethods())
                .map(Method::getName)
                .filter(this::isTest)
                .map(this::toTestCase)
                .toList();

        tests.addAll(testCases);
    }

    public void add(TestCase test) {
        tests.add(test);
    }

    public void run(TestResult result) throws Exception {
        for (TestCase test : tests)
            test.run(result);
    }

    private boolean isTest(String name) {
        return name.startsWith("test");
    }

    private TestCase toTestCase(String name) {
        try {
            Constructor<? extends TestCase> testConstructor = testClass.getDeclaredConstructor(String.class);
            testConstructor.setAccessible(true);
            return testConstructor.newInstance(name);
        }
        catch (Exception ignore) {
            return null;
        }
    }
}
