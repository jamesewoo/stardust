package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/17/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModularArithmetic {

    public static boolean isCongruent(int x, int y, int n) {
        if (x >= y)
            return (x - y) % n == 0;
        else
            return (y - x) % n == 0;
    }

    public static boolean isCoprime(int x, int y) {
        return gcd(x, y) == 1;
    }

    public static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }

    /**
     * Returns true if x is in Zq.
     *
     * @param x An integer
     * @param q
     * @return true if x is in Zq.
     */
    public static boolean isElement(int x, int q) {
        return x >= 0 && x < q;
    }

    public static int add(int x, int y, int q) {
        if (!isElement(x, q) || !isElement(y, q))
            throw new ArithmeticException("element is not in Zq");
        return (x + y) % q;
    }

    public static int subtract(int x, int y, int q) {
        if (!isElement(x, q) || !isElement(y, q))
            throw new ArithmeticException("element is not in Zq");
        return (x - y) >= 0 ? x - y : x - y + q;
    }

    public static int multiply(int x, int y, int q) {
        if (!isElement(x, q) || !isElement(y, q))
            throw new ArithmeticException("element is not in Zq");
        return (x * y) % q;
    }

    public static Integer inverse(int x, int q) {
        if (!isElement(x, q))
            throw new ArithmeticException("element is not in Zq");
        if (isCoprime(x, q)) {
            return extendedGcd(x, q);
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

    public static int reduceModP(int x, int p) {
        if (x >= p)
            return x % p;
        else if (x < 0)
            x += (1 - x / p) * p;
        return x;
    }
}
