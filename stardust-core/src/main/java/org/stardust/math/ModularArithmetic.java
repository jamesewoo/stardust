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
        return (x - y) % n == 0;
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

    public static int inverse(int x, int q) {
        if (!isElement(x, q))
            throw new ArithmeticException("element is not in Zq");
        if (isCoprime(x, q)) {
            return euclideanAlgorithm(x, q);
        } else throw new ArithmeticException("x is not coprime with q");
    }

    public static int euclideanAlgorithm(int x, int q) {
        // TODO
        return -1;
    }
}
