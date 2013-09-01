package org.stardust.math;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Methods for testing {@link FiniteGroup}s
 */
public class FiniteGroupTestHelper<T> extends GroupTestHelper<T> {

    private final FiniteGroup<T> G;

    public FiniteGroupTestHelper(FiniteGroup<T> group) {
        super(group);
        this.G = group;
    }

    public void testFiniteOrder1(T a) {
        assertEquals(BigInteger.ZERO, G.getGroupOrder().remainder(G.getOrder(a)));
    }

    public void testFiniteOrder2(T a, BigInteger x, BigInteger y, BigInteger m) {
        BigInteger r = G.getOrder(a);
        assertEquals(G.multiply(a, x), G.multiply(a, x.add(m.multiply(r))));
        if (ModMath.isCongruent(x, y, r)) {
            assertEquals(G.multiply(a, x), G.multiply(a, y));
        }
        if (G.multiply(a, x).equals(G.multiply(a, y))) {
            assertTrue(ModMath.isCongruent(x, y, r));
        }
    }

    public void testCyclicSubgroup1(T a, BigInteger m) {
        BigInteger r = G.getOrder(a);
        FiniteGroup<T> H = G.getCyclicSubgroup(a);
        assertEquals(r, H.getGroupOrder());
        if (ModMath.isCoprime(m, r))
            assertEquals(H, G.getCyclicSubgroup(G.multiply(a, m)));
    }

    public void testCyclicSubgroup2(T a, BigInteger m, BigInteger n) {
        BigInteger r = G.getOrder(a);
        if (m.compareTo(BigInteger.ZERO) >= 0)
            assertEquals(G.multiply(a, m), G.multiply(a, m.add(n.multiply(r))));
    }
}
