package testing;

import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    List<TestCase> tests = new ArrayList<>();

    public void add(TestCase test) {
        tests.add(test);
    }

    public void run(TestResult result) throws Exception {
        for (TestCase test : tests)
            test.run(result);
    }
}
