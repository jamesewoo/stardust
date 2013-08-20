package org.stardust.math;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomogeneousCoordinates implements Coordinates {

    private final int x;

    private final int y;

    private final int z;

    public HomogeneousCoordinates(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HomogeneousCoordinates) {
            HomogeneousCoordinates coords = (HomogeneousCoordinates) obj;
            return new EqualsBuilder().append(x, coords.getX()).append(y, coords.getY()).append(z, coords.getZ()).isEquals();
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
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
