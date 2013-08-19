package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class EllipticCurveGroup implements FiniteGroup<Coordinates> {

    private EllipticCurveParameters params;

    public EllipticCurveGroup(EllipticCurveParameters params) {
        this.params = params;
    }

    @Override
    public int getGroupOrder() {
        return params.getP();
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
    public Coordinates operate(Coordinates p, Coordinates q) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Coordinates operate(AffineCoordinates p, AffineCoordinates q) {
        if (!p.equals(q) && p.getX() == q.getX())
            return Coordinates.POINT_AT_INFINITY;
        else if (!p.equals(q) && p.getX() != q.getX()) {
            int d = (q.getY() - p.getY()) / (q.getX() - p.getX());
            int x = d * d - p.getX() - q.getX();
            int y = (p.getX() - x) * (q.getY() - p.getY()) / (q.getX() - p.getX()) - p.getY();
            x = ModularArithmetic.reduceModP(x, params.getP());
            y = ModularArithmetic.reduceModP(y, params.getP());
            return new AffineCoordinates(x, y);
        } else if (p.equals(q) && p.getY() == 0)
            return Coordinates.POINT_AT_INFINITY;
        else {
            int d = (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY());
            int x = d * d - 2 * p.getX();
            int y = (p.getX() - x) * (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()) - p.getY();
            x = ModularArithmetic.reduceModP(x, params.getP());
            y = ModularArithmetic.reduceModP(y, params.getP());
            return new AffineCoordinates(x, y);
        }
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

    public boolean isValid(AffineCoordinates p) {
        return p.getY() * p.getY() == p.getX() * p.getX() * p.getX() + params.getA() * p.getX() + params.getB();
    }

}
