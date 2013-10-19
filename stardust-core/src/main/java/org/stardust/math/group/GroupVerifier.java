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

    public boolean checkClosure(T a, T b) throws GroupException {
        if (G.contains(a) && G.contains(b)) {
            return G.contains(G.operate(a, b));
        } else {
            throw new GroupException("a and b must be elements of G");
        }
    }

    public boolean checkAssociativity(T a, T b, T c) throws GroupException {
        if (G.contains(a) && G.contains(b) && G.contains(c)) {
            return G.operate(G.operate(a, b), c)
                    .equals(G.operate(a, G.operate(b, c)));
        } else {
            throw new GroupException("a, b, and c must be elements of G");
        }
    }

    public boolean checkIdentity(T a) throws GroupException {
        if (G.contains(a)) {
            T e = G.getIdentity();
            return G.contains(e)
                    && a.equals(G.operate(e, a))
                    && a.equals(G.operate(a, e));
        } else {
            throw new GroupException("a must be an element of G");
        }
    }

    public boolean checkInverse(T a) throws GroupException {
        if (G.contains(a)) {
            T aInv = G.getInverse(a);
            T e = G.getIdentity();
            return e.equals(G.operate(a, aInv)) &&
                    e.equals(G.operate(aInv, a));
        } else {
            throw new GroupException("a must be an element of G");
        }
    }
}
