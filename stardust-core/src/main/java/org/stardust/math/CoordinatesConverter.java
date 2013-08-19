package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/19/13
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinatesConverter {

    public boolean isEqual(AffineCoordinates p, HomogeneousCoordinates q, int order) {
        int zInverse = ModularArithmetic.inverse(q.getZ(), order);
        return (p.getX() == ModularArithmetic.multiply(q.getX(), zInverse, order))
                && (p.getY() == ModularArithmetic.multiply(q.getY(), zInverse, order));
    }

    public HomogeneousCoordinates convert(AffineCoordinates p) {
        return new HomogeneousCoordinates(p.getX(), p.getY(), 1);
    }

    public AffineCoordinates convert(HomogeneousCoordinates p, int order) {
        int zInverse = ModularArithmetic.inverse(p.getZ(), order);
        return new AffineCoordinates(ModularArithmetic.multiply(p.getX(), zInverse, order),
                ModularArithmetic.multiply(p.getY(), zInverse, order));
    }
}
