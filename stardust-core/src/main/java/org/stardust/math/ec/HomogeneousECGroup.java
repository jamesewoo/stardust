package org.stardust.math.ec;

import org.stardust.math.*;

/**
 * An elliptic curve group using homogeneous coordinates.
 */
public class HomogeneousECGroup implements ECGroup<HomogeneousCoordinates> {

    public static final HomogeneousCoordinates POINT_AT_INFINITY = new HomogeneousCoordinates(null, null, null);

    private EllipticCurveParameters params;

    private FiniteField field;


    public HomogeneousECGroup(EllipticCurveParameters params) throws EllipticCurveException {
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (ModMath.isCongruent(4 * field.pow(params.getA(), 3) + 27 * field.pow(params.getB(), 2), 0, params.getP()))
            throw new EllipticCurveException("invalid discriminant");
    }

    /**
     * Gets the point at infinity.
     *
     * @return the point at infinity.
     */
    public HomogeneousCoordinates getPointAtInfinity() {
        return POINT_AT_INFINITY;
    }

    @Override
    public int getGroupOrder() {
        return params.getP();
    }

    @Override
    public int getOrder(HomogeneousCoordinates a) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<HomogeneousCoordinates> getCyclicSubgroup(HomogeneousCoordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HomogeneousCoordinates operate(HomogeneousCoordinates p, HomogeneousCoordinates q) {
        CoordinatesConverter converter = new CoordinatesConverter();
        if (!isValid(converter.convert(p, params.getP())) || !isValid(converter.convert(q, params.getP())))
            throw new RuntimeException("invalid point");
        if (!p.equals(POINT_AT_INFINITY)
                && !q.equals(POINT_AT_INFINITY)
                && !p.equals(q)
                && !p.equals(q.getInverse())) {
            int u = ModMath.reduce(q.getY() * p.getZ() - p.getY() * q.getZ(), params.getP());
            int v = ModMath.reduce(q.getX() * p.getZ() - p.getX() * q.getZ(), params.getP());
            int x = v * (q.getZ() * (p.getZ() * u * u - 2 * p.getX() * v * v) - v * v * v);
            int y = q.getZ() * (3 * p.getX() * u * v * v - p.getY() * v * v * v - p.getZ() * u * u * u) + u * v * v * v;
            int z = v * v * v * p.getZ() * q.getZ();
            return new HomogeneousCoordinates(ModMath.reduce(x, params.getP()),
                    ModMath.reduce(y, params.getP()), ModMath.reduce(z, params.getP()));
        } else if (!p.equals(POINT_AT_INFINITY)
                && !q.equals(POINT_AT_INFINITY)
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
    public HomogeneousCoordinates operateN(HomogeneousCoordinates a, int n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public HomogeneousCoordinates getIdentity() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public HomogeneousCoordinates getInverse(HomogeneousCoordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isElement(HomogeneousCoordinates a) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid(AffineCoordinates p) {
        if (!field.isElement(p.getX()) || !field.isElement(p.getY()))
            return false;
        return field.pow(p.getY(), 2) == field.pow(p.getX(), 3) + params.getA() * p.getX() + params.getB();
    }

}
