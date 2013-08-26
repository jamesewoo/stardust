package org.stardust.math.ec;

import org.junit.Before;
import org.junit.Test;
import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteGroupTestHelper;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link AffineECGroup}
 */
public class AffineECGroupTest {

    private AffineECGroup G;
    private FiniteGroupTestHelper<AffineCoordinates> helper;

    @Before
    public void init() throws EllipticCurveException {
        // TODO specify g and n
        ECParameters params = new ECParameters(BigInteger.valueOf(61), BigInteger.valueOf(-1), BigInteger.ZERO, null, null);
        this.G = new AffineECGroup(params);
        this.helper = new FiniteGroupTestHelper<>(G);
    }

    @Test
    public void testOperate() {
        AffineCoordinates q = new AffineCoordinates(0, 0);
        AffineCoordinates res = helper.operationTest(AffineECGroup.POINT_AT_INFINITY, q);
        assertEquals(q, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOperateInvalidPoint() {
        AffineCoordinates q = new AffineCoordinates(0, 1);
        AffineCoordinates res = helper.operationTest(AffineECGroup.POINT_AT_INFINITY, q);
    }
}
