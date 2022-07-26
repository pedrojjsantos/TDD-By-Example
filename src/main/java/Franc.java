public class Franc {
    private int amount;

    Franc(int amount) {
        this.amount = amount;
    }

    public Franc times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    public boolean equals(Object obj) {
        Franc that = (Franc) obj;
        return this.amount == that.amount;
    }
}
