package org.stardust.math.ec;

import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteField;
import org.stardust.math.FiniteGroup;

import java.math.BigInteger;

/**
 * An elliptic curve group using affine coordinates.
 */
public class AffineECGroup implements ECGroup<AffineCoordinates> {

    public static final AffineCoordinates POINT_AT_INFINITY = new AffineCoordinates(null, null);

    private final ECParameters params;

    private final FiniteField field;

    public AffineECGroup(ECParameters params) throws EllipticCurveException {
        if (params.getP().compareTo(val(3)) <= 0)
            throw new IllegalArgumentException("field characteristic cannot be less than or equal to 3");
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (field.isCongruent(getDiscriminant(), BigInteger.ZERO))
            throw new EllipticCurveException("invalid discriminant");
    }

    @Override
    public BigInteger getGroupOrder() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
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
     * <p/>
     * To an arbitrary pair of elliptic curve points P and Q specified by
     * their affine coordinates P=(x1,y1) and Q=(x2,y2), the group operation
     * assigns a third point R = P*Q with the coordinates (x3,y3).  These
     * coordinates are computed as follows:
     * <p/>
     * <tt>
     * if P is (@,@),
     * R = Q
     * else if Q is (@,@),
     * R = P
     * else if P is not equal to Q and x1 is equal to x2,
     * R = (@,@)
     * else if P is not equal to Q and x1 is not equal to x2,
     * x3 = ((y2-y1)/(x2-x1))^2 - x1 - x2 mod p and
     * y3 = (x1-x3)*(y2-y1)/(x2-x1) - y1 mod p
     * else if P is equal to Q and y1 is equal to 0,
     * R = (@,@)
     * else    // P is equal to Q and y1 is not equal to 0
     * x3 = ((3*x1^2 + a)/(2*y1))^2 - 2*x1 mod p and
     * y3 = (x1-x3)*(3*x1^2 + a)/(2*y1) - y1 mod p.
     * </tt>
     *
     * @param p a point on the elliptic curve
     * @param q a point on the elliptic curve
     * @return the result of the group operation
     */
    @Override
    public AffineCoordinates operate(AffineCoordinates p, AffineCoordinates q) {
        if (!isElement(p) || !isElement(q))
            throw new IllegalArgumentException("invalid point in group operation");

        BigInteger x1 = p.getX();
        BigInteger y1 = p.getY();
        BigInteger x2 = q.getX();
        BigInteger y2 = q.getY();
        if (POINT_AT_INFINITY.equals(p))
            return q;
        else if (POINT_AT_INFINITY.equals(q))
            return p;
        else if (!p.equals(q) && x1.equals(x2))
            return POINT_AT_INFINITY;
        else if (!p.equals(q) && !x1.equals(x2)) {
            BigInteger x3 = (y2.subtract(y1).divide(x2.subtract(x1))).pow(2).subtract(x1).subtract(x2);
            BigInteger y3 = (x1.subtract(x3)).multiply(y2.subtract(y1)).divide(x2.subtract(x1)).subtract(y1);
            return new AffineCoordinates(field.reduce(x3), field.reduce(y3));
        } else if (p.equals(q) && y1.equals(BigInteger.ZERO))
            return POINT_AT_INFINITY;
        else {
            BigInteger x3 = ((val(3).multiply(x1.pow(2)).add(params.getA())).divide(val(2).multiply(y1))).pow(2)
                    .subtract(val(2).multiply(x1));
            BigInteger y3 = (x1.subtract(x3)).multiply(val(3).multiply(x1.pow(2)).add(params.getA()))
                    .divide(val(2).multiply(y1)).subtract(y1);
            return new AffineCoordinates(field.reduce(x3), field.reduce(y3));
        }
    }

    @Override
    public AffineCoordinates operateN(AffineCoordinates a, int n) {
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
     * @param p A point
     * @return true if the given point satisfies the curve equation; false otherwise.
     */
    @Override
    public boolean isElement(AffineCoordinates p) {
        if (POINT_AT_INFINITY.equals(p))
            return true;
        return p.getY().pow(2).equals(p.getX().pow(3).add(params.getA().multiply(p.getX())).add(params.getB()));
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