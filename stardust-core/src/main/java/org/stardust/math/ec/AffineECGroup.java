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
        if (params.getP() <= 3)
            throw new IllegalArgumentException("field characteristic cannot be less than or equal to 3");
        this.params = params;
        this.field = new FiniteField(params.getP());
        if (field.isCongruent(getDiscriminant().intValue(), 0))
            throw new EllipticCurveException("invalid discriminant");
    }

    @Override
    public int getGroupOrder() {
        return 0; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getOrder(AffineCoordinates a) {
        return 0; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<AffineCoordinates> getCyclicSubgroup(AffineCoordinates a) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     *
     * @param p a point on the elliptic curve
     * @param q a point on the elliptic curve
     * @return the result of the group operation
     */
    @Override
    public AffineCoordinates operate(AffineCoordinates p, AffineCoordinates q) {
        if (!isElement(p) || !isElement(q))
            throw new IllegalArgumentException("invalid point in group operation");

        if (POINT_AT_INFINITY.equals(p))
            return q;
        else if (POINT_AT_INFINITY.equals(q))
            return p;
        else if (!p.equals(q) && p.getX() == q.getX())
            return POINT_AT_INFINITY;
        else if (!p.equals(q) && p.getX() != q.getX()) {
            int x3 = field.pow((q.getY() - p.getY()) / (q.getX() - p.getX()), 2) - p.getX() - q.getX();
            int y3 = (p.getX() - x3) * (q.getY() - p.getY()) / (q.getX() - p.getX()) - p.getY();
            return new AffineCoordinates(field.reduce(x3), field.reduce(y3));
        } else if (p.equals(q) && p.getY() == 0)
            return POINT_AT_INFINITY;
        else {
            int x3 = field.pow((3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()), 2) - 2 * p.getX();
            int y3 = (p.getX() - x3) * (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()) - p.getY();
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
    public boolean isElement(AffineCoordinates p) {
        if (POINT_AT_INFINITY.equals(p))
            return true;
        return field.pow(p.getY(), 2) == field.pow(p.getX(), 3) + params.getA() * p.getX() + params.getB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getDiscriminant() {
        return BigInteger.valueOf(field.reduce(-16 * (4 * field.pow(params.getA(), 3) + 27 * field.pow(params.getB(), 2))));
    }
}