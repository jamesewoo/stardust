package org.stardust.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoordinatesTest {

    @Test
    public void testEquals() {
        assertEquals(Coordinates.POINT_AT_INFINITY, Coordinates.POINT_AT_INFINITY);
        assertEquals(Coordinates.POINT_AT_INFINITY, Coordinates.POINT_AT_INFINITY.getInverse());
    }
}
