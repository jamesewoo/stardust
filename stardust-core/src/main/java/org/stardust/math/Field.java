package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/16/13
 * Time: 7:04 PM
 */
public interface Field<T> {

    public T add(T a, T b);

    public T multiply(T a, T b);

    public T getAdditiveIdentity();

    public T getMultiplicativeIdentity();

    public T getAdditiveInverse(T a);

    public T getMultiplicativeInverse(T a);

    public boolean isElement(T a);

}
