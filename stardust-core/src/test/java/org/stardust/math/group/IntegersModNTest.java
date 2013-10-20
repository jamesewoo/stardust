package org.stardust.math.group;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

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
        IntegersModN zModN = new IntegersModN(3);

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
    public void testOperateN() {
        IntegersModN zModN = new IntegersModN(3);

        assertEquals(ZERO, zModN.operateN(0, 0));
        assertEquals(ZERO, zModN.operateN(0, 1));
        assertEquals(ZERO, zModN.operateN(0, 2));

        assertEquals(ZERO, zModN.operateN(1, 0));
        assertEquals(ONE, zModN.operateN(1, 1));
        assertEquals(valueOf(2), zModN.operateN(1, 2));
        assertEquals(ZERO, zModN.operateN(1, 3));

        assertEquals(ZERO, zModN.operateN(2, 0));
        assertEquals(valueOf(2), zModN.operateN(2, 1));
        assertEquals(ONE, zModN.operateN(2, 2));
        assertEquals(ZERO, zModN.operateN(2, 3));

        // test element 3, which is congruent to 0 (mod 3)
        assertEquals(ZERO, zModN.operateN(3, 0));
        assertEquals(ZERO, zModN.operateN(3, 1));
        assertEquals(ZERO, zModN.operateN(3, 2));
    }

    @Test
    public void testGroupProperties() {
        IntegersModN zModN = new IntegersModN(3);
        AbelianGroupVerifier<BigInteger> verifier = new AbelianGroupVerifier<>(zModN);
        Iterable<BigInteger> elements = Arrays.asList(ZERO, ONE, valueOf(2));

        assertTrue(verifier.verifyAll(elements));
    }
}
