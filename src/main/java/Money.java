public class Money {
    protected int amount;

    @Override
    public boolean equals(Object obj) {
        Money that = (Money) obj;
        return this.amount == that.amount;
    }
}
