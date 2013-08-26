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
        if (params.getP().compareTo(val(3)) <= 0)
            throw new IllegalArgumentException("field characteristic cannot be less than or equal to 3");
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (field.isCongruent(getDiscriminant(), BigInteger.ZERO))
            throw new EllipticCurveException("invalid discriminant");
        converter = new CoordinatesConverter();
    }

    @Override
    public BigInteger getGroupOrder() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BigInteger getOrder(HomogeneousCoordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<HomogeneousCoordinates> getCyclicSubgroup(HomogeneousCoordinates a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     * <p/>
     * <tt>
     * Let P1=(X1,Y1,Z1) and P2=(X2,Y2,Z2) be points on the elliptic curve,
     * and let u = Y2 * Z1 - Y1 * Z2 and v = X2 * Z1 - X1 * Z2.
     * The product P3=(X3,Y3,Z3) = P1 * P2 is given by:
     * <p/>
     * if P1 is the point at infinity,
     * P3 = P2
     * else if P2 is the point at infinity,
     * P3 = P1
     * else if u is not equal to 0 but v is equal to 0,
     * P3 = (0,1,0)
     * else if both u and v are not equal to 0,
     * X3 = v * (Z2 * (Z1 * u^2 - 2 * X1 * v^2) - v^3)
     * Y3 = Z2 * (3 * X1 * u * v^2 - Y1 * v^3 - Z1 * u^3) + u * v^3
     * Z3 = v^3 * Z1 * Z2
     * else    // P2 equals P1, P3 = P1 * P1
     * w = 3 * X1^2 + a * Z1^2
     * X3 = 2 * Y1 * Z1 * (w^2 - 8 * X1 * Y1^2 * Z1)
     * Y3 = 4 * Y1^2 * Z1 * (3 * w * X1 - 2 * Y1^2 * Z1) - w^3
     * Z3 = 8 * (Y1 * Z1)^3
     * </tt>
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

        BigInteger X1 = p1.getX();
        BigInteger Y1 = p1.getY();
        BigInteger Z1 = p1.getZ();
        BigInteger X2 = p2.getX();
        BigInteger Y2 = p2.getY();
        BigInteger Z2 = p2.getZ();
        BigInteger u = Y2.multiply(Z1).subtract(Y1.multiply(Z2));
        BigInteger v = X2.multiply(Z1).subtract(X1.multiply(Z2));
        if (!u.equals(BigInteger.ZERO) && v.equals(BigInteger.ZERO))
            return POINT_AT_INFINITY;
        else if (!u.equals(BigInteger.ZERO) && !v.equals(BigInteger.ZERO)) {
            BigInteger x3 = v.multiply(Z2.multiply(Z1.multiply(u.pow(2)).subtract(val(2).multiply(X1).multiply(v.pow(2)))).subtract(v.pow(3)));
            BigInteger y3 = Z2.multiply(val(3).multiply(X1).multiply(u).multiply(v.pow(2)).subtract(Y1.multiply(v.pow(3))).subtract(Z1.multiply(u.pow(3))).add(u.multiply(v.pow(3))));
            BigInteger z3 = null;
            return new HomogeneousCoordinates(field.reduce(x3), field.reduce(y3), field.reduce(z3));
        } else {
            BigInteger w = val(3).multiply(X1.pow(2)).add(params.getA().multiply(Z1.pow(2)));
            BigInteger x3 = val(2).multiply(Y1).multiply(Z1)
                    .multiply((w.pow(2)).subtract(val(8).multiply(X1).multiply(Y1.pow(2)).multiply(Z1)));
            BigInteger y3 = null;
            BigInteger z3 = null;
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
        return affineP.getY().pow(2).equals(affineP.getX().pow(3).add(params.getA().multiply(affineP.getX())).add(params.getB()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getDiscriminant() {
        return val(-16).multiply(val(4).multiply(params.getA().pow(3)).add(val(27).multiply(params.getB().pow(2))));
    }

    private BigInteger val(long x) {
        return BigInteger.valueOf(x);
    }
}
