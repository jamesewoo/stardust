package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Group<T> {

    public T operate(T a, T b);

    public T operateN(T a, int n);

    public T getIdentity();

    public T getInverse(T a);

    public boolean isElement(T a);

    public boolean hasFiniteOrder(T a);
}
