public abstract class Money {
    protected String currency;
    protected int amount;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    abstract public Money times(int multiplier);

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }
    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    @Override
    public boolean equals(Object obj) {
        Money that = (Money) obj;
        return this.amount == that.amount && this.getClass() == that.getClass();
    }

    public String currency() {
        return currency;
    }
}
