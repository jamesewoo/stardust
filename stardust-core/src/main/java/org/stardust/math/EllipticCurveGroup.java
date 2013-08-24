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

    private FiniteField field;

    public EllipticCurveGroup(EllipticCurveParameters params) throws EllipticCurveException {
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (ModMath.isCongruent(4 * field.pow(params.getA(), 3) + 27 * field.pow(params.getB(), 2), 0, params.getP()))
            throw new EllipticCurveException("invalid discriminant");
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
        if (!isValid(p) || !isValid(q))
            throw new RuntimeException("invalid point");
        if (!p.equals(q) && p.getX() == q.getX())
            return AffineCoordinates.getPointAtInfinity();
        else if (!p.equals(q) && p.getX() != q.getX()) {
            int d = (q.getY() - p.getY()) / (q.getX() - p.getX());
            int x = d * d - p.getX() - q.getX();
            int y = (p.getX() - x) * (q.getY() - p.getY()) / (q.getX() - p.getX()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        } else if (p.equals(q) && p.getY() == 0)
            return AffineCoordinates.getPointAtInfinity();
        else {
            int d = (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY());
            int x = d * d - 2 * p.getX();
            int y = (p.getX() - x) * (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        }
    }

    public Coordinates operate(HomogeneousCoordinates p, HomogeneousCoordinates q) {
        CoordinatesConverter converter = new CoordinatesConverter();
        if (!isValid(converter.convert(p, params.getP())) || !isValid(converter.convert(q, params.getP())))
            throw new RuntimeException("invalid point");
        if (!p.equals(HomogeneousCoordinates.getPointAtInfinity())
                && !q.equals(HomogeneousCoordinates.getPointAtInfinity())
                && !p.equals(q)
                && !p.equals(q.getInverse())) {
            int u = ModMath.reduce(q.getY() * p.getZ() - p.getY() * q.getZ(), params.getP());
            int v = ModMath.reduce(q.getX() * p.getZ() - p.getX() * q.getZ(), params.getP());
            int x = v * (q.getZ() * (p.getZ() * u * u - 2 * p.getX() * v * v) - v * v * v);
            int y = q.getZ() * (3 * p.getX() * u * v * v - p.getY() * v * v * v - p.getZ() * u * u * u) + u * v * v * v;
            int z = v * v * v * p.getZ() * q.getZ();
            return new HomogeneousCoordinates(ModMath.reduce(x, params.getP()),
                    ModMath.reduce(y, params.getP()), ModMath.reduce(z, params.getP()));
        } else if (!p.equals(HomogeneousCoordinates.getPointAtInfinity())
                && !q.equals(HomogeneousCoordinates.getPointAtInfinity())
                && p.equals(q)) {
            int w = ModMath.reduce(3 * p.getX() * p.getX() + params.getA() * p.getZ() * p.getZ(), params.getP());
            int x = 2 * p.getY() * p.getZ() * (w * w - 8 * p.getX() * p.getY() * p.getY() * p.getZ());
            int y = 4 * p.getY() * p.getY() * p.getZ() * (3 * w * p.getX() - 2 * p.getY() * p.getY() * p.getZ()) - w * w * w;
            int z = 8 * (p.getY() * p.getZ()) * (p.getY() * p.getZ()) * (p.getY() * p.getZ());
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
        if (!field.isElement(p.getX()) || !field.isElement(p.getY()))
            return false;
        return field.pow(p.getY(), 2) == field.pow(p.getX(), 3) + params.getA() * p.getX() + params.getB();
    }

}
