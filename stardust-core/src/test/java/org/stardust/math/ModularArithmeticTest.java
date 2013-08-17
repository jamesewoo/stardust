package org.stardust.math;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModularArithmeticTest {

    @Test
    public void testGcd() {
        Assert.assertEquals(12, ModularArithmetic.gcd(48, 180));
        Assert.assertEquals(12, ModularArithmetic.gcd(180, 48));
    }

    @Test
    public void testAdd() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int r = ModularArithmetic.add(x, y, q);
                if (x + y < q)
                    Assert.assertEquals(x + y, r);
                else
                    Assert.assertEquals(x + y - q, r);
            }
        }
    }

    @Test
    public void testSubtract() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int r = ModularArithmetic.subtract(x, y, q);
                if (x - y >= 0)
                    Assert.assertEquals(x - y, r);
                else
                    Assert.assertEquals(x - y + q, r);
            }
        }
    }

    @Test
    public void testMultiply() {
        int q = 7;
        for (int x = 0; x < q; ++x) {
            for (int y = 0; y < q; ++y) {
                int r = ModularArithmetic.multiply(x, y, q);
                Assert.assertEquals((x * y) % q, r);
            }
        }
    }

    @Test
    public void testDivide() {
    }
}
