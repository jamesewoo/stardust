package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
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
        if (x >= y)
            return (x - y) % n == 0;
        else
            return (y - x) % n == 0;
    }

    /**
     * Returns true if x and y are coprime.
     *
     * @param x An integer
     * @param y An integer
     * @return true if x and y are coprime.
     */
    public static boolean isCoprime(int x, int y) {
        return gcd(x, y) == 1;
    }

    public static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x - y * (x / y));
    }

    /**
     * Returns true if x is in Zq.
     *
     * @param x An integer
     * @param q An integer
     * @return true if x is in Zq.
     */
    public static boolean isElement(int x, int q) {
        return x >= 0 && x < q;
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
        return reduce(x + y, q);
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
        return reduce(x - y, q);
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
        return reduce(x * y, q);
    }

    /**
     * Returns the inverse of x modulo q.
     *
     * @param x An integer in Zq
     * @param q An integer
     * @return The inverse of x in Zq, if it exists; null otherwise
     */
    public static Integer inverse(int x, int q) {
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
    public static int extendedGcd(int a, int b) {
        int x_2 = 1;
        int x_1 = 0;
        int r_2 = a;
        int r_1 = b;
        int r = r_2;
        int x;

        while (r != 0) {
            int q = r_2 / r_1;
            r = r_2 - q * r_1;
            x = x_2 - q * x_1;
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
    public static int reduce(int x, int q) {
        if (x >= q)
            return x % q;
        else if (x < 0)
            x += (1 - x / q) * q;
        return x;
    }

    /**
     * Returns x^exponent mod p
     *
     * @param x        An integer
     * @param exponent An integer exponent
     * @param p        An integer
     * @return Returns x^exponent mod p
     */
    public static int pow(int x, int exponent, int p) {
        int res = 1;
        for (int i = 1; i <= Math.abs(exponent); ++i) {
            res *= x;
        }
        res = reduce(res, p);
        if (exponent >= 0)
            return res;
        else
            return inverse(res, p);
    }
}
