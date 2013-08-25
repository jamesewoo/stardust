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

    public Integer getX() {
        if (x == null)
            return null;
        return x.intValue();
    }

    public Integer getY() {
        if (y == null)
            return null;
        return y.intValue();
    }

    public Integer getZ() {
        if (z == null)
            return null;
        return z.intValue();
    }
}
