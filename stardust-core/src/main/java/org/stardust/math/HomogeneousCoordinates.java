package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomogeneousCoordinates implements Coordinates {

    private final BigInteger x;

    private final BigInteger y;

    private final BigInteger z;

    public static final HomogeneousCoordinates POINT_AT_INFINITY = new HomogeneousCoordinates(null, null, null);

    /**
     * Gets the point at infinity.
     *
     * @return the point at infinity.
     */
    public static Coordinates getPointAtInfinity() {
        return POINT_AT_INFINITY;
    }

    public HomogeneousCoordinates(int x, int y, int z) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
        this.z = BigInteger.valueOf(z);
    }

    public HomogeneousCoordinates(BigInteger x, BigInteger y, BigInteger z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == POINT_AT_INFINITY && obj == POINT_AT_INFINITY)
            return true;
        else if (obj instanceof HomogeneousCoordinates) {
            HomogeneousCoordinates coords = (HomogeneousCoordinates) obj;
            return new EqualsBuilder().append(getX(), coords.getX())
                    .append(getY(), coords.getY()).append(getZ(), coords.getZ()).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).append(z).toHashCode();
    }

    @Override
    public Coordinates getInverse() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getX() {
        return x.intValue();
    }

    public int getY() {
        return y.intValue();
    }

    public int getZ() {
        return z.intValue();
    }
}
