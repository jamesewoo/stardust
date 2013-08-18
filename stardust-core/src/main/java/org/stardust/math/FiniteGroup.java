package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FiniteGroup<T> extends Group<T> {

    public int getGroupOrder();

    public int getOrder(T a);

    public FiniteGroup<T> getCyclicSubgroup(T a);
}
