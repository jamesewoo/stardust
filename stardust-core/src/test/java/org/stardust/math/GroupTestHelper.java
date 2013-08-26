package org.stardust.math;


import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Methods for testing {@link Group}s
 */
public class GroupTestHelper<T> {

    private Group<T> G;

    public GroupTestHelper(Group<T> group) {
        this.G = group;
    }

    public T operationTest(T a, T b) {
        T res = G.operate(a, b);
        assertTrue(G.isElement(res));
        return res;
    }

    public void associativityTest(T a, T b, T c) {
        assertEquals(G.operate(G.operate(a, b), c), G.operate(a, G.operate(b, c)));
    }

    public void identityTest(T a) {
        assertEquals(a, G.operate(G.getIdentity(), a));
        assertEquals(a, G.operate(a, G.getIdentity()));
    }

    public void inverseTest(T a) {
        T b = G.getInverse(a);
        assertEquals(G.getIdentity(), G.operate(a, b));
        assertEquals(G.getIdentity(), G.operate(b, a));
    }

    public void operateNTest1(T a, BigInteger x) {
        assertEquals(G.operateN(G.getInverse(a), x), G.operateN(a, x.negate()));
    }

    public void operateNTest2(T a, BigInteger x, BigInteger y) {
        assertEquals(G.operate(G.operateN(a, x), G.operateN(a, y)), G.operateN(a, x.add(y)));
        assertEquals(G.operateN(a, x.multiply(y)), G.operateN(G.operateN(a, x), y));
        assertEquals(G.operateN(a, x.multiply(y)), G.operateN(G.operateN(a, y), x));
    }

}
