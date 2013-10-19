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
        return G.contains(G.operate(a, b));
    }

    public boolean checkAssociativity(T a, T b, T c) {
        return G.operate(G.operate(a, b), c)
                .equals(G.operate(a, G.operate(b, c)));
    }

    public boolean checkIdentity(T a) {
        return a.equals(G.operate(G.getIdentity(), a)) &&
                a.equals(G.operate(a, G.getIdentity()));
    }

    public boolean checkInverse(T a) {
        T aInv = G.getInverse(a);
        return G.getIdentity().equals(G.operate(a, aInv)) &&
                G.getIdentity().equals(G.operate(aInv, a));
    }

}
