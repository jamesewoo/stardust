package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

/**
 * Homogeneous coordinates.
 */
public class HomogeneousCoordinates implements Coordinates {

    private final BigInteger x;

    private final BigInteger y;

    private final BigInteger z;

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
        if (obj instanceof HomogeneousCoordinates) {
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

    public HomogeneousCoordinates getInverse() {
        return new HomogeneousCoordinates(x, y.negate(), z);
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
