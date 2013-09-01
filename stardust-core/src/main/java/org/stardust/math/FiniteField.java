package org.stardust.math;

import java.math.BigInteger;

/**
 * A finite field with prime characteristic.
 */
public class FiniteField {

    private final BigInteger p;

    /**
     * Creates a finite field with prime characteristic p.
     *
     * @param p the characteristic
     */
    public FiniteField(BigInteger p) {
        this.p = p;
    }

    /**
     * Returns true if x is an element of the field; false otherwise.
     *
     * @param x an element of the field
     * @return true if x is an element of the field; false otherwise.
     */
    public boolean isElement(BigInteger x) {
        return ModMath.isElement(x, p);
    }

    /**
     * Returns x + y mod p
     *
     * @param x an BigInteger
     * @param y an BigInteger
     * @return x + y mod p
     */
    public BigInteger add(BigInteger x, BigInteger y) {
        return ModMath.add(x, y, p);
    }

    /**
     * Returns x - y mod p
     *
     * @param x an BigInteger
     * @param y an BigInteger
     * @return x - y mod p
     */
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return ModMath.subtract(x, y, p);
    }

    /**
     * Returns x * y mod p
     *
     * @param x an BigInteger
     * @param y an BigInteger
     * @return x * y mod p
     */
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return ModMath.multiply(x, y, p);
    }

    /**
     * Returns x times the inverse of y mod p.
     *
     * @param x an element of the field
     * @param y an element of the field
     * @return x / y mod p
     */
    public BigInteger divide(BigInteger x, BigInteger y) {
        BigInteger inv = ModMath.inverse(y, p);
        if (inv != null)
            return ModMath.multiply(x, inv, p);
        return null;
    }

    /**
     * Returns x raised to the <code>exponent</code> power, mod p.
     *
     * @param x        an BigInteger
     * @param exponent an BigInteger
     * @return x raised to the <code>exponent</code> power, mod p.
     */
    public BigInteger pow(BigInteger x, BigInteger exponent) {
        return ModMath.pow(x, exponent, p);
    }

    /**
     * Reduces x so that the result is an element of Zp.
     *
     * @param x An BigInteger
     * @return an element of Zp.
     */
    public BigInteger reduce(BigInteger x) {
        return ModMath.reduce(x, p);
    }

    /**
     * Returns true if x and y are congruent modulo n.
     *
     * @param x An BigInteger
     * @param y An BigInteger
     * @return true if x and y are congruent modulo n.
     */
    public boolean isCongruent(BigInteger x, BigInteger y) {
        return ModMath.isCongruent(x, y, p);
    }

    /**
     * Gets the characteristic of the field.
     *
     * @return the characteristic of the field.
     */
    public BigInteger getCharacteristic() {
        return p;
    }
}
