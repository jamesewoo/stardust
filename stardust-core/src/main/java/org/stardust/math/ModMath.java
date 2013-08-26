package org.stardust.math;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * Modular arithmetic functions.
 */
public class ModMath {
    /**
     * Returns true if x and y are congruent modulo n.
     *
     * @param x An integer
     * @param y An integer
     * @param n An integer
     * @return true if x and y are congruent modulo n.
     */
    public static boolean isCongruent(int x, int y, int n) {
        return isCongruent(valueOf(x), valueOf(y), valueOf(n));
    }

    /**
     * Returns true if x and y are coprime.
     *
     * @param x An integer
     * @param y An integer
     * @return true if x and y are coprime.
     */
    public static boolean isCoprime(int x, int y) {
        return isCoprime(valueOf(x), valueOf(y));
    }

    /**
     * Returns the greatest common denominator of x and y.
     *
     * @param x An integer
     * @param y An integer
     * @return the greatest common denominator of x and y.
     */
    public static int gcd(int x, int y) {
        return gcd(valueOf(x), valueOf(y)).intValue();
    }

    /**
     * Returns true if x is an element of Zq.
     *
     * @param x An integer
     * @param q An integer
     * @return true if x is an element of Zq.
     */
    public static boolean isElement(int x, int q) {
        return isElement(valueOf(x), valueOf(q));
    }

    /**
     * Returns x + y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x + y mod q
     */
    public static int add(int x, int y, int q) {
        return add(valueOf(x), valueOf(y), valueOf(q)).intValue();
    }

    /**
     * Returns x - y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x - y mod q
     */
    public static int subtract(int x, int y, int q) {
        return subtract(valueOf(x), valueOf(y), valueOf(q)).intValue();
    }

    /**
     * Returns x * y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x * y mod q
     */
    public static int multiply(int x, int y, int q) {
        return multiply(valueOf(x), valueOf(y), valueOf(q)).intValue();
    }

    /**
     * Returns the inverse of x modulo q.
     *
     * @param x An integer in Zq
     * @param q An integer
     * @return The inverse of x in Zq, if it exists; null otherwise
     */
    public static Integer inverse(int x, int q) {
        BigInteger inv = inverse(valueOf(x), valueOf(q));
        if (inv != null)
            return inverse(valueOf(x), valueOf(q)).intValue();
        return null;
    }

    /**
     * Returns the Bézout coefficient of <code>a</code>
     *
     * @param a An integer
     * @param b An integer
     * @return the Bézout coefficient of <code>a</code>
     */
    public static int extendedGcd(int a, int b) {
        return extendedGcd(valueOf(a), valueOf(b)).intValue();
    }

    /**
     * Reduces x so that the result is an element of Zq.
     *
     * @param x An integer
     * @param q An integer
     * @return an element of Zq.
     */
    public static int reduce(int x, int q) {
        if (q < 1)
            throw new ArithmeticException("q cannot be less than 1");
        if (x >= q)
            return x % q;
        else if (x < 0)
            x += (1 - x / q) * q;
        return x;
    }

    /**
     * Returns x raised to the <code>exponent</code> power, mod q.
     *
     * @param x        An integer
     * @param exponent An integer exponent
     * @param q        An integer
     * @return x raised to the <code>exponent</code> power, mod q.
     */
    public static Integer pow(int x, int exponent, int q) {
        if (q < 1)
            throw new ArithmeticException("q cannot be less than 1");
        int res = 1;
        for (int i = 1; i <= Math.abs(exponent); ++i) {
            res *= x;
        }
        res = reduce(res, q);
        if (exponent >= 0)
            return res;
        else
            return inverse(res, q);
    }

    /**
     * Returns true if x and y are congruent modulo n.
     *
     * @param x An integer
     * @param y An integer
     * @param n An integer
     * @return true if x and y are congruent modulo n.
     */
    public static boolean isCongruent(BigInteger x, BigInteger y, BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        if (x.compareTo(y) >= 0)
            return (x.subtract(y)).remainder(n).equals(BigInteger.ZERO);
        else
            return (y.subtract(x)).remainder(n).equals(BigInteger.ZERO);
    }

    /**
     * Returns true if x and y are coprime.
     *
     * @param x An integer
     * @param y An integer
     * @return true if x and y are coprime.
     */
    public static boolean isCoprime(BigInteger x, BigInteger y) {
        return gcd(x, y).equals(BigInteger.ONE);
    }

    /**
     * Returns the greatest common denominator of x and y.
     *
     * @param x An integer
     * @param y An integer
     * @return the greatest common denominator of x and y.
     */
    public static BigInteger gcd(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO))
            return x;
        else
            return gcd(y, x.subtract(y.multiply(x.divide(y))));
    }

    /**
     * Returns true if x is an element of Zq.
     *
     * @param x An integer
     * @param q An integer
     * @return true if x is an element of Zq.
     */
    public static boolean isElement(BigInteger x, BigInteger q) {
        return x.compareTo(BigInteger.ZERO) >= 0 && x.compareTo(q) < 0;
    }

    /**
     * Returns x + y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x + y mod q
     */
    public static BigInteger add(BigInteger x, BigInteger y, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        return reduce(x.add(y), q);
    }

    /**
     * Returns x - y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x - y mod q
     */
    public static BigInteger subtract(BigInteger x, BigInteger y, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        return reduce(x.subtract(y), q);
    }

    /**
     * Returns x * y mod q
     *
     * @param x An arbitrary integer
     * @param y An arbitrary integer
     * @param q An arbitrary integer
     * @return x * y mod q
     */
    public static BigInteger multiply(BigInteger x, BigInteger y, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        return reduce(x.multiply(y), q);
    }

    /**
     * Returns the inverse of x modulo q.
     *
     * @param x An integer in Zq
     * @param q An integer
     * @return The inverse of x in Zq, if it exists; null otherwise
     */
    public static BigInteger inverse(BigInteger x, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        if (isCoprime(x, q)) {
            return reduce(extendedGcd(x, q), q);
        } else
            return null;
    }

    /**
     * Returns the Bézout coefficient of <code>a</code>
     *
     * @param a An integer
     * @param b An integer
     * @return the Bézout coefficient of <code>a</code>
     */
    public static BigInteger extendedGcd(BigInteger a, BigInteger b) {
        BigInteger x_2 = BigInteger.ONE;
        BigInteger x_1 = BigInteger.ZERO;
        BigInteger r_2 = a;
        BigInteger r_1 = b;
        BigInteger r = r_2;
        BigInteger x;

        while (!r.equals(BigInteger.ZERO)) {
            BigInteger q = r_2.divide(r_1);
            r = r_2.subtract(q.multiply(r_1));
            x = x_2.subtract(q.multiply(x_1));
            x_2 = x_1;
            x_1 = x;
            r_2 = r_1;
            r_1 = r;
        }
        return x_2;
    }

    /**
     * Reduces x so that the result is an element of Zq.
     *
     * @param x An integer
     * @param q An integer
     * @return an element of Zq.
     */
    public static BigInteger reduce(BigInteger x, BigInteger q) {
        return x.mod(q);
    }

    /**
     * Returns x raised to the <code>exponent</code> power, mod q.
     *
     * @param x        An integer
     * @param exponent An integer exponent
     * @param q        An integer
     * @return x raised to the <code>exponent</code> power, mod q.
     */
    public static BigInteger pow(BigInteger x, BigInteger exponent, BigInteger q) {
        if (q.compareTo(BigInteger.ZERO) <= 0)
            throw new ArithmeticException("q cannot be less or equal to 0");
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= exponent.abs().intValue(); ++i) {
            res = x.multiply(res);
        }
        res = reduce(res, q);
        if (exponent.compareTo(BigInteger.ZERO) >= 0)
            return res;
        else
            return inverse(res, q);
    }
}
