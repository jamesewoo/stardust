package org.stardust.math.field;

import org.stardust.math.ModMath;

import java.math.BigInteger;

/**
 * A finite field with prime characteristic.
 */
public class IntegersModP implements Field<BigInteger> {

    private final BigInteger p;

    /**
     * Creates a finite field with prime characteristic p.
     *
     * @param p the characteristic
     */
    public IntegersModP(BigInteger p) {
        this.p = p;
    }

    /**
     * Returns true if x is an element of the field; false otherwise.
     *
     * @param x an element of the field
     * @return true if x is an element of the field; false otherwise.
     */
    @Override
    public boolean contains(BigInteger x) {
        return ModMath.isElement(x, p);
    }

    /**
     * Returns x + y mod p
     *
     * @param x an BigInteger
     * @param y an BigInteger
     * @return x + y mod p
     */
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return ModMath.add(x, y, p);
    }

    /**
     * Returns x * y mod p
     *
     * @param x an BigInteger
     * @param y an BigInteger
     * @return x * y mod p
     */
    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return ModMath.multiply(x, y, p);
    }

    @Override
    public BigInteger getAdditiveIdentity() {
        return BigInteger.ZERO;
    }

    @Override
    public BigInteger getMultiplicativeIdentity() {
        return BigInteger.ONE;
    }

    @Override
    public BigInteger getAdditiveInverse(BigInteger a) {
        return a.negate().mod(p);
    }

    @Override
    public BigInteger getMultiplicativeInverse(BigInteger a) {
        return a.modInverse(p);
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
