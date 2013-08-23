package org.stardust.math;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ModMath}
 */
public class ModMathTest {

    @Test
    public void testIsCongruent() {
        assertTrue(ModMath.isCongruent(-9, 14, 23));
        assertTrue(ModMath.isCongruent(14, -9, 23));
    }

    @Test
    public void testGcd() {
        assertEquals(12, ModMath.gcd(48, 180));
        assertEquals(12, ModMath.gcd(180, 48));
    }

    @Test
    public void testAdd() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int result = ModMath.add(x, y, q);
                if (x + y < q)
                    assertEquals(x + y, result);
                else
                    assertEquals(x + y - q, result);
            }
        }
    }

    @Test
    public void testSubtract() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int result = ModMath.subtract(x, y, q);
                if (x - y >= 0)
                    assertEquals(x - y, result);
                else
                    assertEquals(x - y + q, result);
            }
        }
    }

    @Test
    public void testMultiply() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int result = ModMath.multiply(x, y, q);
                assertEquals((x * y) % q, result);
            }
        }
    }

    @Test
    public void testInverse() {
        assertTrue(ModMath.isCongruent(47, ModMath.inverse(23, 120), 120));
        assertTrue(ModMath.isCongruent(-9, ModMath.inverse(5, 23), 23));
        assertTrue(ModMath.isCongruent(14, ModMath.inverse(5, 23), 23));
        assertEquals(Integer.valueOf(4), ModMath.inverse(2, 7));
        assertEquals(Integer.valueOf(2), ModMath.inverse(4, 7));
        assertEquals(Integer.valueOf(1), ModMath.inverse(1, 7));
    }

    @Test
    public void testExtendedEuclidean() {
        assertEquals(-9, ModMath.extendedGcd(120, 23));
        assertEquals(47, ModMath.extendedGcd(23, 120));
    }

    @Test
    public void testReduceModP() {
        assertEquals(14, ModMath.reduce(-55, 23));
        assertEquals(14, ModMath.reduce(-32, 23));
        assertEquals(14, ModMath.reduce(-9, 23));
        assertEquals(14, ModMath.reduce(37, 23));
        assertEquals(14, ModMath.reduce(60, 23));
        assertEquals(14, ModMath.reduce(83, 23));
    }

    @Test
    public void testPow() {
        assertEquals(Integer.valueOf(1), ModMath.pow(2, 0, 7));
        assertEquals(Integer.valueOf(2), ModMath.pow(2, 1, 7));
        assertEquals(Integer.valueOf(4), ModMath.pow(2, 2, 7));
        assertEquals(Integer.valueOf(1), ModMath.pow(2, 3, 7));
        assertEquals(ModMath.inverse(1, 7), ModMath.pow(2, -3, 7));
        assertEquals(ModMath.inverse(4, 7), ModMath.pow(2, -2, 7));
        assertEquals(ModMath.inverse(2, 7), ModMath.pow(2, -1, 7));
    }
}
