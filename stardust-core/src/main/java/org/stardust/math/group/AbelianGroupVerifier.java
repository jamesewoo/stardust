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

    public boolean checkCommutativity(T a, T b) throws GroupException {
        if (G.contains(a) && G.contains(b)) {
            return G.operate(a, b).equals(G.operate(b, a));
        } else {
            throw new GroupException("a and b must be elements of G");
        }
    }
}
