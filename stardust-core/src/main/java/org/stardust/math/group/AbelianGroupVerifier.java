package org.stardust.math.group;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/19/13
 * Time: 1:13 PM
 */
public class AbelianGroupVerifier<T> extends GroupVerifier<T> {

    private final Group<T> G;

    public AbelianGroupVerifier(Group<T> group) {
        super(group);
        this.G = group;
    }

    public boolean checkCommutativity(T a, T b) {
        if (G.contains(a) && G.contains(b)) {
            return G.operate(a, b).equals(G.operate(b, a));
        } else {
            throw new GroupException("a and b must be elements of G");
        }
    }

    @Override
    public boolean verifyAll(Iterable<T> elements) {
        for (T i : elements) {
            if (!checkIdentity(i))
                throw new GroupException("identity check failed");

            if (!checkInverse(i))
                throw new GroupException("inverse check failed");

            for (T j : elements) {
                if (!checkClosure(i, j))
                    throw new GroupException("closure check failed");

                if (!checkCommutativity(i, j))
                    throw new GroupException("commutativity check failed");

                for (T k : elements) {
                    if (!checkAssociativity(i, j, k))
                        throw new GroupException("associativity check failed");
                }
            }
        }
        return true;
    }
}
