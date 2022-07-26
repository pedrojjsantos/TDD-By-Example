public abstract class Money {
    protected int amount;

    abstract public Money times(int multiplier);

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }
    public static Money franc(int amount) {
        return new Franc(amount);
    }

    @Override
    public boolean equals(Object obj) {
        Money that = (Money) obj;
        return this.amount == that.amount && this.getClass() == that.getClass();
    }
}
