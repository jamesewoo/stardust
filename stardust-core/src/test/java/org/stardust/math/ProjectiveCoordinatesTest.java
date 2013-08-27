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
public class ProjectiveCoordinatesTest {
    @Test
    public void testEquals() {
        ProjectiveCoordinates p1 = new ProjectiveCoordinates(0, 1, 1);
        ProjectiveCoordinates p2 = new ProjectiveCoordinates(0, 1, 1);
        assertEquals(p1, p2);
    }
}
