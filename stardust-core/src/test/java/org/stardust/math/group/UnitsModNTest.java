package org.stardust.math.group;

import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/19/13
 * Time: 12:14 PM
 */
public class UnitsModNTest {

    @Test
    public void testOperate() {
        UnitsModN units = new UnitsModN(8);

        assertEquals(ONE, units.operate(1, 1));
        assertEquals(valueOf(3), units.operate(1, 3));
        assertEquals(valueOf(5), units.operate(1, 5));
        assertEquals(valueOf(7), units.operate(1, 7));

        assertEquals(valueOf(3), units.operate(3, 1));
        assertEquals(ONE, units.operate(3, 3));
        assertEquals(valueOf(7), units.operate(3, 5));
        assertEquals(valueOf(5), units.operate(3, 7));

        assertEquals(valueOf(5), units.operate(5, 1));
        assertEquals(valueOf(7), units.operate(5, 3));
        assertEquals(ONE, units.operate(5, 5));
        assertEquals(valueOf(3), units.operate(5, 7));

        assertEquals(valueOf(7), units.operate(7, 1));
        assertEquals(valueOf(5), units.operate(7, 3));
        assertEquals(valueOf(3), units.operate(7, 5));
        assertEquals(ONE, units.operate(7, 7));
    }

    @Test(expected = ArithmeticException.class)
    public void testOperateOnInvalidElement1() {
        UnitsModN units = new UnitsModN(8);
        units.operate(8, 1);
    }

    @Test(expected = ArithmeticException.class)
    public void testOperateOnInvalidElement2() {
        UnitsModN units = new UnitsModN(8);
        units.operate(1, 8);
    }

    @Test
    public void testOperateN() {
        UnitsModN units = new UnitsModN(8);

        assertEquals(ONE, units.operateN(1, 0));
        assertEquals(ONE, units.operateN(1, 1));
        assertEquals(ONE, units.operateN(1, 2));

        assertEquals(ONE, units.operateN(3, 0));
        assertEquals(valueOf(3), units.operateN(3, 1));
        assertEquals(ONE, units.operateN(3, 2));

        assertEquals(ONE, units.operateN(5, 0));
        assertEquals(valueOf(5), units.operateN(5, 1));
        assertEquals(ONE, units.operateN(5, 2));

        assertEquals(ONE, units.operateN(7, 0));
        assertEquals(valueOf(7), units.operateN(7, 1));
        assertEquals(ONE, units.operateN(7, 2));
    }

    @Test
    public void testGroupProperties() throws GroupException {
        UnitsModN units = new UnitsModN(8);
        AbelianGroupVerifier<BigInteger> verifier = new AbelianGroupVerifier<>(units);
        int[] elements = new int[]{1, 3, 5, 7};

        for (int i : elements) {
            assertTrue(verifier.checkIdentity(valueOf(i)));
            assertTrue(verifier.checkInverse(valueOf(i)));
            for (int j : elements) {
                assertTrue(verifier.checkClosure(valueOf(i), valueOf(j)));
                assertTrue(verifier.checkCommutativity(valueOf(i), valueOf(j)));
                for (int k : elements) {
                    assertTrue(verifier.checkAssociativity(valueOf(i), valueOf(j), valueOf(k)));
                }
            }
        }
    }
}
