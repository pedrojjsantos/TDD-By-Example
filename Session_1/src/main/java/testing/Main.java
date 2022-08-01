package testing;

public class Main {
    public static void main(String[] args) throws Exception {
        new TestCaseTest("testRunning").run();
        new TestCaseTest("testSetUp").run();
    }
}
