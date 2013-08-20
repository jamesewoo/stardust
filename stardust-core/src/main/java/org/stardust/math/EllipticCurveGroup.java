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
        if (p instanceof AffineCoordinates && q instanceof AffineCoordinates)
            return operate((AffineCoordinates) p, (AffineCoordinates) q);
        if (p instanceof HomogeneousCoordinates && q instanceof HomogeneousCoordinates)
            return operate((HomogeneousCoordinates) p, (HomogeneousCoordinates) q);
        else
            throw new IllegalArgumentException("unknown coordinate system");
    }

    public Coordinates operate(AffineCoordinates p, AffineCoordinates q) {
        if (!p.equals(q) && p.getX() == q.getX())
            return Coordinates.POINT_AT_INFINITY;
        else if (!p.equals(q) && p.getX() != q.getX()) {
            int d = (q.getY() - p.getY()) / (q.getX() - p.getX());
            int x = d * d - p.getX() - q.getX();
            int y = (p.getX() - x) * (q.getY() - p.getY()) / (q.getX() - p.getX()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        } else if (p.equals(q) && p.getY() == 0)
            return Coordinates.POINT_AT_INFINITY;
        else {
            int d = (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY());
            int x = d * d - 2 * p.getX();
            int y = (p.getX() - x) * (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        }
    }

    public Coordinates operate(HomogeneousCoordinates p1, HomogeneousCoordinates p2) {
        if (!p1.equals(Coordinates.POINT_AT_INFINITY)
                && !p2.equals(Coordinates.POINT_AT_INFINITY)
                && !p1.equals(p2)
                && !p1.equals(p2.getInverse())) {
            int u = ModMath.reduce(p2.getY() * p1.getZ() - p1.getY() * p2.getZ(), params.getP());
            int v = ModMath.reduce(p2.getX() * p1.getZ() - p1.getX() * p2.getZ(), params.getP());
            int x = v * (p2.getZ() * (p1.getZ() * u * u - 2 * p1.getX() * v * v) - v * v * v);
            int y = p2.getZ() * (3 * p1.getX() * u * v * v - p1.getY() * v * v * v - p1.getZ() * u * u * u) + u * v * v * v;
            int z = v * v * v * p1.getZ() * p2.getZ();
            return new HomogeneousCoordinates(ModMath.reduce(x, params.getP()),
                    ModMath.reduce(y, params.getP()), ModMath.reduce(z, params.getP()));
        } else if (!p1.equals(Coordinates.POINT_AT_INFINITY)
                && !p2.equals(Coordinates.POINT_AT_INFINITY)
                && p1.equals(p2)) {
            int w = ModMath.reduce(3 * p1.getX() * p1.getX() + params.getA() * p1.getZ() * p1.getZ(), params.getP());
            int x = 2 * p1.getY() * p1.getZ() * (w * w - 8 * p1.getX() * p1.getY() * p1.getY() * p1.getZ());
            int y = 4 * p1.getY() * p1.getY() * p1.getZ() * (3 * w * p1.getX() - 2 * p1.getY() * p1.getY() * p1.getZ()) - w * w * w;
            int z = 8 * (p1.getY() * p1.getZ()) * (p1.getY() * p1.getZ()) * (p1.getY() * p1.getZ());
            return new HomogeneousCoordinates(ModMath.reduce(x, params.getP()),
                    ModMath.reduce(y, params.getP()), ModMath.reduce(z, params.getP()));
        } else
            throw new UnsupportedOperationException("operation undefined");
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
