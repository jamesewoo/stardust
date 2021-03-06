package org.stardust.math.coords;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * Homogeneous coordinates.
 */
public class ProjectiveCoordinates implements Coordinates {

    private final BigInteger x;

    private final BigInteger y;

    private final BigInteger z;

    public ProjectiveCoordinates(int x, int y, int z) {
        this.x = valueOf(x);
        this.y = valueOf(y);
        this.z = valueOf(z);
    }

    public ProjectiveCoordinates(BigInteger x, BigInteger y, BigInteger z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProjectiveCoordinates) {
            ProjectiveCoordinates coords = (ProjectiveCoordinates) obj;
            return new EqualsBuilder().append(getX(), coords.getX())
                    .append(getY(), coords.getY()).append(getZ(), coords.getZ()).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).append(z).toHashCode();
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }

    public BigInteger getZ() {
        return z;
    }
}
