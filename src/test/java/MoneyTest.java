import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {
    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);

        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    // Naive implementation:
    // TODO: add tests comparing to a null and comparing to a different type;
    public void testEquality() {
        assertEquals(new Dollar(5), new Dollar(5));
        assertNotEquals(new Dollar(5), new Dollar(6));
    }
}
