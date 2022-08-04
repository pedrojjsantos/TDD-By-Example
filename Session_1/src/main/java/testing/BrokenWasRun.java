package testing;

public class BrokenWasRun extends WasRun {
    BrokenWasRun(String methodName) {
        super(methodName);
    }

    @Override
    public void setUp() {
        super.setUp();
        throw new NullPointerException("Ops");
    }
}
