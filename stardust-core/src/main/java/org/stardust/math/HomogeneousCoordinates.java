package org.stardust.math;

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
