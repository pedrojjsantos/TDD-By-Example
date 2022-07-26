public class Dollar {
    int amount;

    Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

    @Override
    public boolean equals(Object obj) {
        Dollar that = (Dollar) obj;
        return this.amount == that.amount;
    }
}
