package org.stardust.math.field;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/16/13
 * Time: 7:04 PM
 */
public interface Field<T> {

    public T add(T a, T b);

    public T multiply(T a, T b);

    /**
     * Returns the zero element, or the additive identity.
     *
     * @return the zero element
     */
    public T zero();

    /**
     * Returns one, or the multiplicative identity.
     *
     * @return the multiplicative identity
     */
    public T one();

    public T getAdditiveInverse(T a);

    public T getMultiplicativeInverse(T a);

    public boolean contains(T a);

}
