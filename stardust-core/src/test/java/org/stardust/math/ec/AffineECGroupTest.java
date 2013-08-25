package org.stardust.math.ec;

import org.junit.Before;
import org.junit.Test;
import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteGroupTestHelper;

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
        ECParameters params = new ECParameters(61, -1, 0, 0, 0);
        this.G = new AffineECGroup(params);
        this.helper = new FiniteGroupTestHelper<AffineCoordinates>(G);
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
