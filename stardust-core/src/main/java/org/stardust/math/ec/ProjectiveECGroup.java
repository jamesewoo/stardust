package org.stardust.math.ec;

import org.stardust.math.field.FiniteField;
import org.stardust.math.coords.AffineCoordinates;
import org.stardust.math.coords.ProjectiveCoordinates;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * An elliptic curve group using homogeneous coordinates.
 */
public class ProjectiveECGroup extends ECGroup<ProjectiveCoordinates> {

    public static final ProjectiveCoordinates POINT_AT_INFINITY = new ProjectiveCoordinates(1, 1, 0);

    private final ECParameters params;
    private final FiniteField field;

    /**
     * Creates a new elliptic curve using projective coordinates.
     *
     * @param params the curve parameters
     * @throws EllipticCurveException if the discriminant is invalid
     */
    public ProjectiveECGroup(ECParameters params) throws EllipticCurveException {
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
     *
     * @param s a point on the elliptic curve
     * @param t a point on the elliptic curve
     * @return the result of the group operation
     */
    @Override
    public ProjectiveCoordinates operate(ProjectiveCoordinates s, ProjectiveCoordinates t) {
        if (!contains(s) || !contains(t))
            throw new IllegalArgumentException("invalid point in group operation");

        return ecFullAdd(s, t);
    }

    @Override
    public ProjectiveCoordinates operateN(ProjectiveCoordinates a, BigInteger n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns the point at infinity.
     *
     * @return the point at infinity.
     */
    @Override
    public ProjectiveCoordinates getIdentity() {
        return POINT_AT_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectiveCoordinates getInverse(ProjectiveCoordinates a) {
        if (POINT_AT_INFINITY.equals(a))
            return POINT_AT_INFINITY;
        // TODO
        return null;
    }

    /**
     * Returns true if the given point satisfies the curve equation.
     *
     * @param q A point
     * @return true if the given point satisfies the curve equation; false otherwise.
     */
    @Override
    public boolean contains(ProjectiveCoordinates q) {
        if (POINT_AT_INFINITY.equals(q))
            return true;
        AffineCoordinates p = ecAffinify(q);
        BigInteger t = p.getX().pow(3).add(params.getA().multiply(p.getX())).add(params.getB());
        return field.isCongruent(p.getY().pow(2), t);
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
