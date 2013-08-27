package org.stardust.math.ec;

import org.stardust.math.AffineCoordinates;
import org.stardust.math.FiniteGroup;
import org.stardust.math.ModMath;
import org.stardust.math.ProjectiveCoordinates;

import java.math.BigInteger;

/**
 * An elliptic curve group.
 */
public abstract class ECGroup<T> implements FiniteGroup<T> {

    private ECParameters params;

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
        return null;  //To change body of created methods use File | Settings | File Templates.
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
