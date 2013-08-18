package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Coordinates {

    public static final Coordinates POINT_AT_INFINITY = new Coordinates() {
        @Override
        public Coordinates getInverse() {
            return this;
        }
    };

    public Coordinates getInverse();
}
