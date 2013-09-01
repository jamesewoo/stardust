package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * Affine coordinates.
 */
public class AffineCoordinates implements Coordinates {

    private final BigInteger x;

    private final BigInteger y;

    public AffineCoordinates(int x, int y) {
        this.x = valueOf(x);
        this.y = valueOf(y);
    }

    public AffineCoordinates(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AffineCoordinates) {
            AffineCoordinates coords = (AffineCoordinates) obj;
            return new EqualsBuilder().append(getX(), coords.getX())
                    .append(getY(), coords.getY()).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).toHashCode();
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }

}
