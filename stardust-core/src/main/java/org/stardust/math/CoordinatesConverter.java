package org.stardust.math;

import org.stardust.math.ec.AffineECGroup;
import org.stardust.math.ec.ProjectiveECGroup;

import java.math.BigInteger;

/**
 * Converts between coordinates systems.
 */
public class CoordinatesConverter {

    public ProjectiveCoordinates convert(AffineCoordinates p) {
        if (AffineECGroup.POINT_AT_INFINITY.equals(p))
            return ProjectiveECGroup.POINT_AT_INFINITY;
        return new ProjectiveCoordinates(p.getX(), p.getY(), BigInteger.ONE);
    }

    public AffineCoordinates convert(ProjectiveCoordinates p, BigInteger order) {
        if (BigInteger.ZERO.equals(p.getZ()))
            return AffineECGroup.POINT_AT_INFINITY;
        BigInteger zInverse = ModMath.inverse(p.getZ(), order);
        if (zInverse != null)
            return new AffineCoordinates(ModMath.multiply(p.getX(), zInverse.pow(2), order),
                    ModMath.multiply(p.getY(), zInverse.pow(3), order));
        return null;
    }
}
