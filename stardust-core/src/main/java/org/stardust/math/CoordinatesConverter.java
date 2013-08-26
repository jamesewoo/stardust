package org.stardust.math;

import java.math.BigInteger;

/**
 * Converts between coordinates systems.
 */
public class CoordinatesConverter {

    public boolean isEqual(AffineCoordinates p, HomogeneousCoordinates q, BigInteger order) {
        BigInteger zInverse = ModMath.inverse(q.getZ(), order);
        return (p.getX().equals(ModMath.multiply(q.getX(), zInverse, order)))
                && (p.getY().equals(ModMath.multiply(q.getY(), zInverse, order)));
    }

    public HomogeneousCoordinates convert(AffineCoordinates p) {
        return new HomogeneousCoordinates(p.getX(), p.getY(), BigInteger.ONE);
    }

    public AffineCoordinates convert(HomogeneousCoordinates p, BigInteger order) {
        BigInteger zInverse = ModMath.inverse(p.getZ(), order);
        return new AffineCoordinates(ModMath.multiply(p.getX(), zInverse, order),
                ModMath.multiply(p.getY(), zInverse, order));
    }
}
