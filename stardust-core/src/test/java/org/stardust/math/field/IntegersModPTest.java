package org.stardust.math.field;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by evadrone on 12/17/13.
 */
public class IntegersModPTest {

    @Test
    public void thatAdditionIsWellDefined() {
        IntegersModP Zp = new IntegersModP(3);

        assertEquals(ZERO, Zp.add(0, 0));
        assertEquals(ONE, Zp.add(0, 1));
        assertEquals(valueOf(2), Zp.add(0, 2));
        assertEquals(ZERO, Zp.add(0, 3));

        assertEquals(ONE, Zp.add(1, 0));
        assertEquals(valueOf(2), Zp.add(1, 1));
        assertEquals(ZERO, Zp.add(1, 2));
        assertEquals(ONE, Zp.add(1, 3));

        assertEquals(valueOf(2), Zp.add(2, 0));
        assertEquals(ZERO, Zp.add(2, 1));
        assertEquals(ONE, Zp.add(2, 2));
        assertEquals(valueOf(2), Zp.add(2, 3));

        assertEquals(ZERO, Zp.add(3, 0));
        assertEquals(ONE, Zp.add(3, 1));
        assertEquals(valueOf(2), Zp.add(3, 2));
        assertEquals(ZERO, Zp.add(3, 3));
    }

    @Test
    public void thatMultiplicationIsWellDefined() {
        IntegersModP Zp = new IntegersModP(3);

        assertEquals(ZERO, Zp.multiply(0, 0));
        assertEquals(ZERO, Zp.multiply(0, 1));
        assertEquals(ZERO, Zp.multiply(0, 2));
        assertEquals(ZERO, Zp.multiply(0, 3));

        assertEquals(ZERO, Zp.multiply(1, 0));
        assertEquals(ONE, Zp.multiply(1, 1));
        assertEquals(valueOf(2), Zp.multiply(1, 2));
        assertEquals(ZERO, Zp.multiply(1, 3));

        assertEquals(ZERO, Zp.multiply(2, 0));
        assertEquals(valueOf(2), Zp.multiply(2, 1));
        assertEquals(ONE, Zp.multiply(2, 2));
        assertEquals(ZERO, Zp.multiply(2, 3));

        assertEquals(ZERO, Zp.multiply(3, 0));
        assertEquals(ZERO, Zp.multiply(3, 1));
        assertEquals(ZERO, Zp.multiply(3, 2));
        assertEquals(ZERO, Zp.multiply(3, 3));
    }

    @Test
    public void thatFieldPropertiesHold() {
        int prime = 7;
        IntegersModP Zp = new IntegersModP(prime);

        List<BigInteger> elements = new ArrayList<>(prime);
        for (int i = 0; i < prime; ++i)
            elements.add(valueOf(i));

        FieldVerifier<BigInteger> verifier = new FieldVerifier<>(Zp);
        verifier.verifyAll(elements);
    }
}
