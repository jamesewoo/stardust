package org.stardust.math.ec;

import org.stardust.math.*;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * An elliptic curve group using affine coordinates.
 */
public class AffineECGroup extends ECGroup<AffineCoordinates> {

    public static final AffineCoordinates POINT_AT_INFINITY = new AffineCoordinates(null, null);

    private final ECParameters params;

    private final FiniteField field;

    public AffineECGroup(ECParameters params) throws EllipticCurveException {
        super(params);
        if (params.getP().compareTo(val(3)) <= 0)
            throw new IllegalArgumentException("field characteristic cannot be less than or equal to 3");
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (field.isCongruent(getDiscriminant(), BigInteger.ZERO))
            throw new EllipticCurveException("invalid discriminant");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getGroupOrder() {
        return params.getQ();
    }

    @Override
    public BigInteger getOrder(AffineCoordinates a) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<AffineCoordinates> getCyclicSubgroup(AffineCoordinates a) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     *
     * @param s a point on the elliptic curve
     * @param t a point on the elliptic curve
     * @return the result of the group operation
     */
    @Override
    public AffineCoordinates operate(AffineCoordinates s, AffineCoordinates t) {
        if (!isElement(s) || !isElement(t))
            throw new IllegalArgumentException("invalid point in group operation");

        ProjectiveCoordinates res = ecFullAdd(ecProjectify(s), ecProjectify(t));
        return ecAffinify(res);
    }

    @Override
    public AffineCoordinates operateN(AffineCoordinates a, BigInteger n) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns the point at infinity.
     *
     * @return the point at infinity.
     */
    @Override
    public AffineCoordinates getIdentity() {
        return POINT_AT_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AffineCoordinates getInverse(AffineCoordinates a) {
        if (POINT_AT_INFINITY.equals(a))
            return POINT_AT_INFINITY;
        return new AffineCoordinates(a.getX(), a.getY().negate());
    }

    /**
     * Returns true if the given point satisfies the curve equation.
     *
     * @param s A point
     * @return true if the given point satisfies the curve equation; false otherwise.
     */
    @Override
    public boolean isElement(AffineCoordinates s) {
        if (POINT_AT_INFINITY.equals(s))
            return true;
        BigInteger t = s.getX().pow(3).add(params.getA().multiply(s.getX())).add(params.getB());
        return field.isCongruent(s.getY().pow(2), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getDiscriminant() {
        return val(-16).multiply(val(4).multiply(params.getA().pow(3)).add(val(27).multiply(params.getB().pow(2))));
    }

    private BigInteger val(long x) {
        return valueOf(x);
    }
}