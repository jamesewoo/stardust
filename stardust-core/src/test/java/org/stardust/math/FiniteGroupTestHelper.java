package org.stardust.math;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Methods for testing {@link FiniteGroup}s
 */
public class FiniteGroupTestHelper<T> extends GroupTestHelper<T> {

    private FiniteGroup<T> G;

    public FiniteGroupTestHelper(FiniteGroup<T> group) {
        super(group);
        this.G = group;
    }

    public void finiteOrderTest1(T a) {
        assertEquals(BigInteger.ZERO, G.getGroupOrder() .remainder( G.getOrder(a)));
    }

//    public void finiteOrderTest2(T a, int x, BigInteger y, BigInteger m) {
//        BigInteger r = G.getOrder(a);
//        assertEquals(G.operateN(a, x), G.operateN(a, x + m * r));
//        if (ModMath.isCongruent(x, y, r)) {
//            assertEquals(G.operateN(a, x), G.operateN(a, y));
//        }
//        if (G.operateN(a, x).equals(G.operateN(a, y))) {
//            assertTrue(ModMath.isCongruent(x, y, r));
//        }
//    }
//
//    public void cyclicSubgroupTest1(T a, int m) {
//        BigInteger r = G.getOrder(a);
//        FiniteGroup<T> H = G.getCyclicSubgroup(a);
//        assertEquals(r, H.getGroupOrder());
//        if (ModMath.isCoprime(m, r))
//            assertEquals(H, G.getCyclicSubgroup(G.operateN(a, m)));
//    }
//
//    public void cyclicSubgroupTest2(T a, int m, int n) {
//        BigInteger r = G.getOrder(a);
//        if (m >= 0)
//            assertEquals(G.operateN(a, m), G.operateN(a, m + n * r));
//    }
}
