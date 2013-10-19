package org.stardust.math.group;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/19/13
 * Time: 12:07 PM
 */
public class UnitsModN implements Group<BigInteger> {

    private final BigInteger modulus;

    public UnitsModN(Integer modulus) {
        this(valueOf(modulus));
    }

    public UnitsModN(BigInteger modulus) {
        this.modulus = modulus;
    }

    public BigInteger operate(Integer a, Integer b) {
        return operate(valueOf(a), valueOf(b));
    }

    public BigInteger operateN(Integer a, Integer n) {
        return operateN(valueOf(a), valueOf(n));
    }

    public BigInteger getInverse(Integer a) {
        return getInverse(valueOf(a));
    }

    public boolean contains(Integer a) {
        return contains(valueOf(a));
    }

    @Override
    public BigInteger operate(BigInteger a, BigInteger b) {
        if (!contains(a) || !contains(b))
            throw new ArithmeticException("input elements must be relatively prime to modulus");
        return a.multiply(b).mod(modulus);
    }

    @Override
    public BigInteger operateN(BigInteger a, BigInteger n) {
        return a.modPow(n, modulus);
    }

    @Override
    public BigInteger getIdentity() {
        return ONE;
    }

    @Override
    public BigInteger getInverse(BigInteger a) {
        return a.modInverse(modulus);
    }

    @Override
    public boolean contains(BigInteger a) {
        return modulus.gcd(a).equals(ONE);
    }
}
