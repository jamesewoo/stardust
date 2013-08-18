package org.stardust.math;


import org.junit.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTest<T> {

    protected Group<T> group;

    public GroupTest(Group<T> group) {
        this.group = group;
    }

    public void operationTest(T a, T b) {
        Assert.assertTrue(group.isElement(a));
        Assert.assertTrue(group.isElement(b));
        Assert.assertTrue(group.isElement(group.operate(a, b)));
    }

    public void associativityTest(T a, T b, T c) {
        Assert.assertEquals(group.operate(group.operate(a, b), c), group.operate(a, group.operate(b, c)));
    }

    public void identityTest(T a) {
        Assert.assertEquals(a, group.operate(group.getIdentity(), a));
        Assert.assertEquals(a, group.operate(a, group.getIdentity()));
    }

    public void inverseTest(T a) {
        T b = group.getInverse(a);
        Assert.assertEquals(group.getIdentity(), group.operate(a, b));
        Assert.assertEquals(group.getIdentity(), group.operate(b, a));
    }

    public void operateNTest1(T a, int x) {
        if (x > 0) {
            Assert.assertEquals(group.operateN(group.getInverse(a), x), group.operateN(a, -x));
        }
    }

    public void operateNTest2(T a, int x, int y) {
        Assert.assertEquals(group.operate(group.operateN(a, x), group.operateN(a, y)), group.operateN(a, x + y));
        Assert.assertEquals(group.operateN(a, x * y), group.operateN(group.operateN(a, x), y));
        Assert.assertEquals(group.operateN(a, x * y), group.operateN(group.operateN(a, y), x));
    }
}
