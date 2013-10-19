package org.stardust.math.group;

import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/18/13
 * Time: 8:58 PM
 */
public class IntegersModNTest {

    @Test
    public void testOperate() {
        IntegersModN zModN = new IntegersModN(valueOf(3));

        assertEquals(ZERO, zModN.operate(0, 0));
        assertEquals(ONE, zModN.operate(0, 1));
        assertEquals(valueOf(2), zModN.operate(0, 2));
        assertEquals(ONE, zModN.operate(1, 0));
        assertEquals(valueOf(2), zModN.operate(1, 1));
        assertEquals(ZERO, zModN.operate(1, 2));
        assertEquals(valueOf(2), zModN.operate(2, 0));
        assertEquals(ZERO, zModN.operate(2, 1));
        assertEquals(ONE, zModN.operate(2, 2));

        // test element 3, which is congruent to 0 (mod 3)
        assertEquals(ZERO, zModN.operate(3, 0));
        assertEquals(ONE, zModN.operate(3, 1));
        assertEquals(valueOf(2), zModN.operate(3, 2));
        assertEquals(ZERO, zModN.operate(0, 3));
        assertEquals(ONE, zModN.operate(1, 3));
        assertEquals(valueOf(2), zModN.operate(2, 3));
        assertEquals(ZERO, zModN.operate(3, 3));
    }

    @Test
    public void testGroupConditions() {
        IntegersModN zModN = new IntegersModN(3);
        GroupVerifier<BigInteger> verifier = new GroupVerifier<>(zModN);
        int[] elements = new int[]{0, 1, 2};

        for (int i : elements) {
            for (int j : elements) {
                assertTrue(verifier.checkClosure(valueOf(i), valueOf(j)));
                for (int k : elements) {
                    assertTrue(verifier.checkAssociativity(valueOf(i), valueOf(j), valueOf(k)));
                }
            }
            assertTrue(verifier.checkIdentity(valueOf(i)));
            assertTrue(verifier.checkInverse(valueOf(i)));
        }
    }
}
