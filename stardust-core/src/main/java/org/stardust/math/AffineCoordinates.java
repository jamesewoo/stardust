package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffineCoordinates implements Coordinates {

    private int x;

    private int y;

    public AffineCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AffineCoordinates) {
            AffineCoordinates coords = (AffineCoordinates) obj;
            return new EqualsBuilder().append(x, coords.getX()).append(y, coords.getY()).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).toHashCode();
    }

    @Override
    public Coordinates getInverse() {
        return new AffineCoordinates(x, -y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
