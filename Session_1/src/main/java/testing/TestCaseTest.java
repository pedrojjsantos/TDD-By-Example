package testing;

public class TestCaseTest extends TestCase {
    WasRun test;

    TestCaseTest(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        test = new WasRun("testMethod");
    }

    public void testRunning() throws Exception {
        test.run();
        assert test.wasRun: "test failed";
    }

    public void testSetUp() throws Exception {
        test.run();
        assert test.wasSetUp;
    }

    public static void main(String[] args) throws Exception {
        new TestCaseTest("testRunning").run();
        new TestCaseTest("testSetUp").run();
    }
}
