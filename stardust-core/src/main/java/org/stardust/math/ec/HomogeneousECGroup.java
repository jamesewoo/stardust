package org.stardust.math.ec;

import org.stardust.math.*;

import java.math.BigInteger;

/**
 * An elliptic curve group using homogeneous coordinates.
 */
public class HomogeneousECGroup implements ECGroup<HomogeneousCoordinates> {

    public static final HomogeneousCoordinates POINT_AT_INFINITY = new HomogeneousCoordinates(0, 1, 0);

    private final ECParameters params;
    private final FiniteField field;
    private final CoordinatesConverter converter;


    /**
     * @param params
     * @throws EllipticCurveException
     */
    public HomogeneousECGroup(ECParameters params) throws EllipticCurveException {
        if (params.getP() <= 3)
            throw new IllegalArgumentException("field characteristic cannot be less than or equal to 3");
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (field.isCongruent(getDiscriminant().intValue(), 0))
            throw new EllipticCurveException("invalid discriminant");
        converter = new CoordinatesConverter();
    }

    @Override
    public int getGroupOrder() {
        return 0; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getOrder(HomogeneousCoordinates a) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<HomogeneousCoordinates> getCyclicSubgroup(HomogeneousCoordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     *
     * @param p1 a point on the elliptic curve
     * @param p2 a point on the elliptic curve
     * @return the result of the group operation
     */
    @Override
    public HomogeneousCoordinates operate(HomogeneousCoordinates p1, HomogeneousCoordinates p2) {
        if (!isElement(p1) || !isElement(p2))
            throw new IllegalArgumentException("invalid point in group operation");

        if (POINT_AT_INFINITY.equals(p1))
            return p2;
        else if (POINT_AT_INFINITY.equals(p2))
            return p1;

        int u = field.reduce(p2.getY() * p1.getZ() - p1.getY() * p2.getZ());
        int v = field.reduce(p2.getX() * p1.getZ() - p1.getX() * p2.getZ());
        if (u != 0 && v == 0)
            return POINT_AT_INFINITY;
        else if (u != 0 && v != 0) {
            int x3 = v * (p2.getZ() * (p1.getZ() * u * u - 2 * p1.getX() * v * v) - v * v * v);
            int y3 = p2.getZ() * (3 * p1.getX() * u * v * v - p1.getY() * v * v * v - p1.getZ() * u * u * u) + u * v * v * v;
            int z3 = v * v * v * p1.getZ() * p2.getZ();
            return new HomogeneousCoordinates(field.reduce(x3), field.reduce(y3), field.reduce(z3));
        } else {
            int w = field.reduce(3 * p1.getX() * p1.getX() + params.getA() * p1.getZ() * p1.getZ());
            int x3 = 2 * p1.getY() * p1.getZ() * (w * w - 8 * p1.getX() * p1.getY() * p1.getY() * p1.getZ());
            int y3 = 4 * p1.getY() * p1.getY() * p1.getZ() * (3 * w * p1.getX() - 2 * p1.getY() * p1.getY() * p1.getZ()) - w * w * w;
            int z3 = 8 * field.pow(p1.getY() * p1.getZ(), 3);
            return new HomogeneousCoordinates(field.reduce(x3), field.reduce(y3), field.reduce(z3));
        }
    }

    @Override
    public HomogeneousCoordinates operateN(HomogeneousCoordinates a, int n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns the point at infinity.
     *
     * @return the point at infinity.
     */
    @Override
    public HomogeneousCoordinates getIdentity() {
        return POINT_AT_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HomogeneousCoordinates getInverse(HomogeneousCoordinates a) {
        if (POINT_AT_INFINITY.equals(a))
            return POINT_AT_INFINITY;
        // TODO
        return null;
    }

    /**
     * Returns true if the given point satisfies the curve equation.
     *
     * @param p A point
     * @return true if the given point satisfies the curve equation; false otherwise.
     */
    @Override
    public boolean isElement(HomogeneousCoordinates p) {
        if (POINT_AT_INFINITY.equals(p))
            return true;
        AffineCoordinates affineP = converter.convert(p, params.getP());
        return field.pow(affineP.getY(), 2) == field.pow(affineP.getX(), 3) + params.getA() * affineP.getX() + params.getB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getDiscriminant() {
        return BigInteger.valueOf(field.reduce(-16 * (4 * field.pow(params.getA(), 3) + 27 * field.pow(params.getB(), 2))));
    }
}
