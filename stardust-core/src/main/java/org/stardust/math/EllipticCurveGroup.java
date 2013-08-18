package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class EllipticCurveGroup implements FiniteGroup<Coordinates> {

    public EllipticCurveGroup(EllipticCurveParameters params){

    }
    @Override
    public int getGroupOrder() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getOrder(Coordinates a) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<Coordinates> getCyclicSubgroup(Coordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Coordinates operate(Coordinates a, Coordinates b) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Coordinates operateN(Coordinates a, int n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Coordinates getIdentity() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Coordinates getInverse(Coordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isElement(Coordinates a) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasFiniteOrder(Coordinates a) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
