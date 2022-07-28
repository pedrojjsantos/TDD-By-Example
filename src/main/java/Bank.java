import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Pair, Integer> rates = new HashMap<>();

    public Money reduce(Expression src, String to) {
        return src.reduce(this, to);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    private class Pair {
        String from;
        String to;

        Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Pair that = (Pair) obj;
            return this.from.equals(that.from) && this.to.equals(that.to);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
