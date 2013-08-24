package org.stardust.math.ec;

/**
 * An elliptic curve exception.
 */
public class EllipticCurveException extends Exception {

    public EllipticCurveException() {
        super();
    }

    public EllipticCurveException(String message) {
        super(message);
    }

    public EllipticCurveException(String message, Throwable cause) {
        super(message, cause);
    }

    public EllipticCurveException(Throwable cause) {
        super(cause);
    }

    protected EllipticCurveException(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
