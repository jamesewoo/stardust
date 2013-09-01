package org.stardust.math.ec;

import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteGroup;
import org.stardust.math.ModMath;
import org.stardust.math.ProjectiveCoordinates;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * An elliptic curve group.
 */
public abstract class ECGroup<T> implements FiniteGroup<T> {

    private final ECParameters params;

    protected ECGroup(ECParameters params) {
        this.params = params;
    }

    /**
     * Returns the discriminant of the elliptic curve group.
     *
     * @return the discriminant of the elliptic curve group.
     */
    public abstract BigInteger getDiscriminant();

    protected ProjectiveCoordinates ecProjectify(AffineCoordinates s) {
        if (AffineECGroup.POINT_AT_INFINITY.equals(s))
            return ProjectiveECGroup.POINT_AT_INFINITY;
        return new ProjectiveCoordinates(s.getX(), s.getY(), BigInteger.ONE);
    }

    protected AffineCoordinates ecAffinify(ProjectiveCoordinates s) {
        if (BigInteger.ZERO.equals(s.getZ()))
            return AffineECGroup.POINT_AT_INFINITY;
        BigInteger zInverse = ModMath.inverse(s.getZ(), params.getP());
        if (zInverse != null)
            return new AffineCoordinates(ModMath.multiply(s.getX(), zInverse.pow(2), params.getP()),
                    ModMath.multiply(s.getY(), zInverse.pow(3), getGroupOrder()));
        return null;
    }

//    protected CompressedPoint ecCompress(AffineCoordinates s){
//        return null;  //To change body of created methods use File | Settings | File Templates.
//    }

//    protected AffineCoordinates ecCompress(CompressedPoint sbar){
//        return null;  //To change body of created methods use File | Settings | File Templates.
//    }

//    protected boolean ecIsPointAffine(AffineCoordinates p){
//    }

    protected ProjectiveCoordinates ecDouble(ProjectiveCoordinates s) {
        if (!ModMath.isCongruent(params.getA(), valueOf(-3), params.getP()))
            throw new IllegalArgumentException("expected a congruent to -3 mod p");
        BigInteger t1 = s.getX();
        BigInteger t2 = s.getY();
        BigInteger t3 = s.getZ();
        if (BigInteger.ZERO.equals(t3))
            return ProjectiveECGroup.POINT_AT_INFINITY;
        BigInteger t4 = t3.multiply(t3);
        BigInteger t5 = t1.subtract(t4);
        t4 = t1.add(t4);
        t5 = t4.multiply(t5);
        t4 = valueOf(3).multiply(t5);
        t3 = t3.multiply(t2);
        t3 = valueOf(2).multiply(t3);
        t2 = t2.multiply(t2);
        t5 = t1.multiply(t2);
        t5 = valueOf(4).multiply(t5);
        t1 = t4.multiply(t4);
        t1 = t1.subtract(valueOf(2).multiply(t5));
        t2 = t2.multiply(t2);
        t2 = valueOf(8).multiply(t2);
        t5 = t5.subtract(t1);
        t5 = t4.multiply(t5);
        t2 = t5.subtract(t2);
        return new ProjectiveCoordinates(t1, t2, t3);
    }

    protected ProjectiveCoordinates ecAdd(ProjectiveCoordinates s, ProjectiveCoordinates t) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    protected ProjectiveCoordinates ecFullAdd(ProjectiveCoordinates s, ProjectiveCoordinates t) {
        if (BigInteger.ZERO.equals(s.getZ()))
            return t;
        else if (BigInteger.ZERO.equals(t.getZ()))
            return s;
        ProjectiveCoordinates r = ecAdd(s, t);
        if (new ProjectiveCoordinates(0, 0, 0).equals(r))
            r = ecDouble(s);
        return r;
    }

    protected ProjectiveCoordinates ecFullSub(ProjectiveCoordinates s, ProjectiveCoordinates t) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    protected ProjectiveCoordinates ecMult(BigInteger d, ProjectiveCoordinates t) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    protected ProjectiveCoordinates ecTwinMult(ProjectiveCoordinates s, ProjectiveCoordinates t) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
