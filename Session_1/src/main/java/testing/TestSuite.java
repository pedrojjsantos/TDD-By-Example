package testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite {
    List<TestCase> tests = new ArrayList<>();

    public TestSuite() {}

    private boolean isTestName(String name) {
        return name.startsWith("test");
    }

    public TestSuite(Class<? extends TestCase> testClass) throws Exception {
        List<String> testNames = Arrays.stream(testClass.getMethods())
                .map(Method::getName)
                .filter(this::isTestName)
                .toList();

        Constructor<? extends TestCase> testConstructor =
                testClass.getDeclaredConstructor(String.class);
        testConstructor.setAccessible(true);

        for (String name : testNames)
            tests.add(testConstructor.newInstance(name));
    }

    public void add(TestCase test) {
        tests.add(test);
    }

    public void run(TestResult result) throws Exception {
        for (TestCase test : tests)
            test.run(result);
    }
}
