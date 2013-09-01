package org.stardust.math;


import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Methods for testing {@link Group}s
 */
public class GroupTestHelper<T> {

    private final Group<T> G;

    public GroupTestHelper(Group<T> group) {
        this.G = group;
    }

    public T testAdd(T a, T b) {
        T res = G.add(a, b);
        assertTrue(G.isElement(res));
        return res;
    }

    public void testAssociativity(T a, T b, T c) {
        assertEquals(G.add(G.add(a, b), c), G.add(a, G.add(b, c)));
    }

    public void testIdentity(T a) {
        assertEquals(a, G.add(G.getIdentity(), a));
        assertEquals(a, G.add(a, G.getIdentity()));
    }

    public void testInverse(T a) {
        T b = G.getInverse(a);
        assertEquals(G.getIdentity(), G.add(a, b));
        assertEquals(G.getIdentity(), G.add(b, a));
    }

    public void testMultiply1(T a, BigInteger x) {
        assertEquals(G.multiply(G.getInverse(a), x), G.multiply(a, x.negate()));
    }

    public void testMultiply2(T a, BigInteger x, BigInteger y) {
        assertEquals(G.add(G.multiply(a, x), G.multiply(a, y)), G.multiply(a, x.add(y)));
        assertEquals(G.multiply(a, x.multiply(y)), G.multiply(G.multiply(a, x), y));
        assertEquals(G.multiply(a, x.multiply(y)), G.multiply(G.multiply(a, y), x));
    }

}
