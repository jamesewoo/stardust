package org.stardust.math.group;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/18/13
 * Time: 10:08 PM
 */
public class GroupVerifier<T> {

    private final Group<T> G;

    public GroupVerifier(Group<T> group) {
        this.G = group;
    }

    public boolean checkClosure(T a, T b) {
        if (G.contains(a) && G.contains(b)) {
            return G.contains(G.operate(a, b));
        } else {
            throw new GroupException("a and b must be elements of G");
        }
    }

    public boolean checkAssociativity(T a, T b, T c) {
        if (G.contains(a) && G.contains(b) && G.contains(c)) {
            return G.operate(G.operate(a, b), c)
                    .equals(G.operate(a, G.operate(b, c)));
        } else {
            throw new GroupException("a, b, and c must be elements of G");
        }
    }

    public boolean checkIdentity(T a) {
        if (G.contains(a)) {
            T e = G.getIdentity();
            return G.contains(e)
                    && a.equals(G.operate(e, a))
                    && a.equals(G.operate(a, e));
        } else {
            throw new GroupException("a must be an element of G");
        }
    }

    public boolean checkInverse(T a) {
        if (G.contains(a)) {
            T aInv = G.getInverse(a);
            T e = G.getIdentity();
            return G.contains(aInv)
                    && e.equals(G.operate(a, aInv)) &&
                    e.equals(G.operate(aInv, a));
        } else {
            throw new GroupException("a must be an element of G");
        }
    }

    public boolean verifyAll(Iterable<T> elements) {
        for (T i : elements) {
            if (!checkIdentity(i))
                throw new GroupException("identity check failed");

            if (!checkInverse(i))
                throw new GroupException("inverse check failed");

            for (T j : elements) {
                if (!checkClosure(i, j))
                    throw new GroupException("closure check failed");

                for (T k : elements) {
                    if (!checkAssociativity(i, j, k))
                        throw new GroupException("associativity check failed");
                }
            }
        }
        return true;
    }
}
