package org.stardust.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class FiniteGroupTest<T> extends GroupTest<T> {

    private FiniteGroup<T> G;

    public FiniteGroupTest(FiniteGroup<T> group) {
        super(group);
        this.G = group;
    }

    public void finiteOrderTest1(T a) {
        assertTrue(G.hasFiniteOrder(a));
        assertEquals(0, G.getGroupOrder() % G.getOrder(a));
    }

    public void finiteOrderTest2(T a, int x, int y, int m) {
        int r = G.getOrder(a);
        assertEquals(G.operateN(a, x), G.operateN(a, x + m * r));
        if (ModularArithmetic.isCongruent(x, y, r)) {
            assertEquals(G.operateN(a, x), G.operateN(a, y));
        }
        if (G.operateN(a, x).equals(G.operateN(a, y))) {
            assertTrue(ModularArithmetic.isCongruent(x, y, r));
        }
    }

    public void cyclicSubgroupTest1(T a, int m) {
        int r = G.getOrder(a);
        FiniteGroup<T> H = G.getCyclicSubgroup(a);
        assertEquals(r, H.getGroupOrder());
        if (ModularArithmetic.isCoprime(m, r))
            assertEquals(H, G.getCyclicSubgroup(G.operateN(a, m)));
    }

    public void cyclicSubgroupTest2(T a, int m, int n) {
        int r = G.getOrder(a);
        if (m >= 0)
            assertEquals(G.operateN(a, m), G.operateN(a, m + n * r));
    }
}
