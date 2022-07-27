public class Bank {
    public Money reduce(Expression src, String to) {
        return src.reduce(this, to);
    }

    public int rate(String from, String to) {
        return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
    }
}
