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
}
