package org.stardust.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/19/13
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomogeneousCoordinatesTest {
    @Test
    public void testEquals() {
        HomogeneousCoordinates p1 = new HomogeneousCoordinates(0, 1, 1);
        HomogeneousCoordinates p2 = new HomogeneousCoordinates(0, 1, 1);
        assertEquals(p1, p2);
    }
}
