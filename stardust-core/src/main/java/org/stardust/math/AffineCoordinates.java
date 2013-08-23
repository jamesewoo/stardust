package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffineCoordinates implements Coordinates {

    private final BigInteger x;

    private final BigInteger y;

    public static final AffineCoordinates POINT_AT_INFINITY = new AffineCoordinates(null, null);

    /**
     * Gets the point at infinity.
     *
     * @return the point at infinity.
     */
    public static Coordinates getPointAtInfinity() {
        return POINT_AT_INFINITY;
    }

    public AffineCoordinates(int x, int y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }

    public AffineCoordinates(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == POINT_AT_INFINITY && obj == POINT_AT_INFINITY)
            return true;
        else if (obj instanceof AffineCoordinates) {
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

    @Override
    public Coordinates getInverse() {
        return new AffineCoordinates(x, y.negate());
    }

    public int getX() {
        return x.intValue();
    }


    public int getY() {
        return y.intValue();
    }

}
