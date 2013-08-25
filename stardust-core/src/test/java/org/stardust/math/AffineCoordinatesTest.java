package org.stardust.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/18/13
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffineCoordinatesTest {

    @Test
    public void testEquals() {
        AffineCoordinates p1 = new AffineCoordinates(0, 1);
        assertEquals(p1, new AffineCoordinates(0, 1));
    }
}
