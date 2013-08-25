package org.stardust.math.ec;

import org.stardust.math.FiniteGroup;

import java.math.BigInteger;

/**
 * An elliptic curve group.
 */
public interface ECGroup<T> extends FiniteGroup<T> {

    /**
     * Returns the discriminant of the elliptic curve group.
     *
     * @return the discriminant of the elliptic curve group.
     */
    public BigInteger getDiscriminant();
}
