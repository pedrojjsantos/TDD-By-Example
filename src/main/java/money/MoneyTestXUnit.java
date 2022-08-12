package money;

import testing.TestCase;
import testing.TestResult;
import testing.TestSuite;

import static testing.Assert.*;

public class MoneyTestXUnit extends TestCase {
    MoneyTestXUnit(String test) {
        super(test);
    }

    public void testDollarMultiplication() {
        Money five = Money.dollar(5);

        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    public void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));

        assertNotEquals(Money.dollar(5), Money.franc(5));
    }

    public void testCurrency() {
        assert "USD".equals(Money.dollar(1).currency());
        assert "CHF".equals(Money.franc(1).currency());
    }

    public static void main(String[] args) throws Exception {
        TestResult result = new TestResult();
        TestSuite suite = new TestSuite(MoneyTestXUnit.class);
        suite.run(result);
        System.out.println(result.summary());
        for (String description : result.getFailDescriptions())
            System.out.println(description);
    }
}
