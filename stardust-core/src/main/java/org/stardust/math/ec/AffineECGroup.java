package org.stardust.math.ec;

import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteField;
import org.stardust.math.FiniteGroup;
import org.stardust.math.ModMath;

/**
 * An elliptic curve group using affine coordinates.
 */
public class AffineECGroup implements ECGroup<AffineCoordinates> {

    public static final AffineCoordinates POINT_AT_INFINITY = new AffineCoordinates(null, null);

    private EllipticCurveParameters params;

    private FiniteField field;

    public AffineECGroup(EllipticCurveParameters params) throws EllipticCurveException {
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
    public int getOrder(AffineCoordinates a) {
        return 0; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FiniteGroup<AffineCoordinates> getCyclicSubgroup(AffineCoordinates a) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AffineCoordinates operate(AffineCoordinates p, AffineCoordinates q) {
        if (!isValid(p) || !isValid(q))
            throw new RuntimeException("invalid point");
        if (!p.equals(q) && p.getX() == q.getX())
            return POINT_AT_INFINITY;
        else if (!p.equals(q) && p.getX() != q.getX()) {
            int d = (q.getY() - p.getY()) / (q.getX() - p.getX());
            int x = d * d - p.getX() - q.getX();
            int y = (p.getX() - x) * (q.getY() - p.getY()) / (q.getX() - p.getX()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        } else if (p.equals(q) && p.getY() == 0)
            return POINT_AT_INFINITY;
        else {
            int d = (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY());
            int x = d * d - 2 * p.getX();
            int y = (p.getX() - x) * (3 * p.getX() * p.getX() + params.getA()) / (2 * p.getY()) - p.getY();
            x = ModMath.reduce(x, params.getP());
            y = ModMath.reduce(y, params.getP());
            return new AffineCoordinates(x, y);
        }
    }

    @Override
    public AffineCoordinates operateN(AffineCoordinates a, int n) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AffineCoordinates getIdentity() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AffineCoordinates getInverse(AffineCoordinates a) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isElement(AffineCoordinates a) {
        return false; //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid(AffineCoordinates p) {
        if (!field.isElement(p.getX()) || !field.isElement(p.getY()))
            return false;
        return field.pow(p.getY(), 2) == field.pow(p.getX(), 3) + params.getA() * p.getX() + params.getB();
    }

}