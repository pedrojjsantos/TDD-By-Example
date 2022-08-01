package testing;

public class TestCaseTest extends TestCase {
    TestCaseTest(String name) {
        super(name);
    }

    public void testRunning() throws Exception {
        WasRun test = new WasRun("testMethod");
        assert test.numberOfTestsRan == 0;
        test.run();
        assert test.numberOfTestsRan == 1 : "test failed";
    }
}
