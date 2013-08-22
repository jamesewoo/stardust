package org.stardust.math;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 8/21/13
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
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
