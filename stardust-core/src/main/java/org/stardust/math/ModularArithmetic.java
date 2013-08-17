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
}
