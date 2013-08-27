package org.stardust.math.ec;

import org.junit.Before;
import org.junit.Ignore;
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
        BigInteger p = new BigInteger("ffffffff ffffffff ffffffff fffffffe ffffffff ffffffff".replace(" ", ""), 16);
        BigInteger a = new BigInteger("ffffffff ffffffff ffffffff fffffffe ffffffff fffffffc".replace(" ", ""), 16);
        BigInteger b = new BigInteger("64210519 e59c80e7 0fa7e9ab 72243049 feb8deec c146b9b1".replace(" ", ""), 16);
        BigInteger gx = new BigInteger("188da80e b03090f6 7cbf20eb 43a18800 f4ff0afd 82ff1012".replace(" ", ""), 16);
        BigInteger gy = new BigInteger("07192b95 ffc8da78 631011ed 6b24cdd5 73f977a1 1e794811".replace(" ", ""), 16);
        BigInteger q = new BigInteger("ffffffff ffffffff ffffffff 99def836 146bc9b1 b4d22831".replace(" ", ""), 16);
        ECParameters<AffineCoordinates> params = new ECParameters<>(p, a, b, new AffineCoordinates(gx, gy), q);
        this.G = new AffineECGroup(params);
        this.helper = new FiniteGroupTestHelper<>(G);
    }

    @Ignore
    @Test
    public void testAdd() {
        BigInteger xs = new BigInteger("d458e7d1 27ae671b 0c330266 d2467693 53a01207 3e97acf8".replace(" ", ""), 16);
        BigInteger ys = new BigInteger("32593050 0d851f33 6bddc050 cf7fb11b 5673a164 5086df3b".replace(" ", ""), 16);
        BigInteger xt = new BigInteger("f22c4395 213e9ebe 67ddecdd 87fdbd01 be16fb05 9b9753a4".replace(" ", ""), 16);
        BigInteger yt = new BigInteger("26442409 6af2b359 7796db48 f8dfb41f a9cecc97 691a9c79".replace(" ", ""), 16);
        BigInteger xr = new BigInteger("48e1e409 6b9b8e5c a9d0f1f0 77b8abf5 8e843894 de4d0290".replace(" ", ""), 16);
        BigInteger yr = new BigInteger("408fa77c 797cd7db fb16aa48 a3648d3d 63c94117 d7b6aa4b".replace(" ", ""), 16);
        AffineCoordinates s = new AffineCoordinates(xs, ys);
        AffineCoordinates t = new AffineCoordinates(xt, yt);
        AffineCoordinates r = new AffineCoordinates(xr, yr);
        AffineCoordinates res = G.add(s, t);
        assertEquals(r, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOperateInvalidPoint() {
        AffineCoordinates q = new AffineCoordinates(0, 1);
        helper.operationTest(AffineECGroup.POINT_AT_INFINITY, q);
    }
}
