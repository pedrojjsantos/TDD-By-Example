import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {
    @Test
    public void testDollarMultiplication() {
        Money five = Money.dollar(5);

        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    // Naive implementation:
    // TODO: add tests comparing to a null and comparing to a different type;
    public void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));

        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(6));

        assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);

        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }
}
