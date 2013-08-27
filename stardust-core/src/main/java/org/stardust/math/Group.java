package org.stardust.math;

import java.math.BigInteger;

/**
 * A mathematical group.
 *
 * @param <T> The group element type
 */
public interface Group<T> {

    /**
     * Combines two elements in G and returns a third element in G.
     *
     * @param a An element in G
     * @param b An element in G
     * @return a third element in G.
     */
    public T add(T a, T b);

    /**
     * Returns the result of the group operation applied to <code>a</code> n-1 times.
     *
     * @param a An element in G
     * @param n An element in G
     * @return the result of the group operation applied to <code>a</code> n-1 times.
     */
    public T multiply(T a, BigInteger n);

    /**
     * Returns the identity element of G.
     *
     * @return the identity element of G.
     */
    public T getIdentity();

    /**
     * Returns the inverse element of <code>a</code> in G.
     *
     * @param a An element in G
     * @return the inverse element of <code>a</code> in G.
     */
    public T getInverse(T a);

    /**
     * Returns true if <code>a</code> is an element of G.
     *
     * @param a An element
     * @return true if <code>a</code> is an element of G; false otherwise
     */
    public boolean isElement(T a);

}
