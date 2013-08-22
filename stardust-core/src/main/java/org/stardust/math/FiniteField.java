package org.stardust.math;

/**
 * A finite field.
 */
public class FiniteField {

    private int p;

    /**
     * Creates a finite field with characteristic p.
     *
     * @param p
     */
    public FiniteField(int p) {
        this.p = p;
    }

    /**
     * Returns true if x is an element of the field; false otherwise.
     *
     * @param x an element of the field
     * @return true if x is an element of the field; false otherwise.
     */
    public boolean isElement(int x) {
        return ModMath.isElement(x, p);
    }

    public int add(int x, int y) {
        return ModMath.add(x, y, p);
    }

    public int subtract(int x, int y) {
        return ModMath.subtract(x, y, p);
    }

    public int multiply(int x, int y) {
        return ModMath.multiply(x, y, p);
    }

    /**
     * Returns x times the inverse of y mod p.
     *
     * @param x an element of the field
     * @param y an element of the field
     * @return x / y mod p
     */
    public Integer divide(int x, int y) {
        Integer inv = ModMath.inverse(y, p);
        if (inv != null)
            return ModMath.multiply(x, inv, p);
        return null;
    }

    /**
     * Returns x to the <code>exponent</code> power.
     *
     * @param x        an integer
     * @param exponent an integer
     * @return x to the <code>exponent</code> power.
     */
    public int pow(int x, int exponent) {
        return ModMath.pow(x, exponent, p);
    }

    /**
     * Gets the characteristic of the field.
     *
     * @return the characteristic of the field.
     */
    public int getP() {
        return p;
    }
}
