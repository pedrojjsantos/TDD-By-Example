package money;

public class Money implements Expression {
    protected String currency;
    protected int amount;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }
    @Override
    public boolean equals(Object obj) {
        Money that = (Money) obj;
        return this.amount == that.amount && this.currency.equals(that.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    public String currency() {
        return currency;
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
}
