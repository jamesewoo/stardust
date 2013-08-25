package org.stardust.math;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTest<T> {

    private final Group<T> G;

    public GroupTest(Group<T> group) {
        this.G = group;
    }

    public void operationTest(T a, T b) {
        assertTrue(G.isElement(a));
        assertTrue(G.isElement(b));
        assertTrue(G.isElement(G.operate(a, b)));
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

    public void operateNTest1(T a, int x) {
        if (x > 0) {
            assertEquals(G.operateN(G.getInverse(a), x), G.operateN(a, -x));
        }
    }

    public void operateNTest2(T a, int x, int y) {
        assertEquals(G.operate(G.operateN(a, x), G.operateN(a, y)), G.operateN(a, x + y));
        assertEquals(G.operateN(a, x * y), G.operateN(G.operateN(a, x), y));
        assertEquals(G.operateN(a, x * y), G.operateN(G.operateN(a, y), x));
    }
}
