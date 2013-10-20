package org.stardust.math.field;

/**
 * Created with IntelliJ IDEA.
 * User: evadrone
 * Date: 10/20/13
 * Time: 12:56 PM
 */
public class FieldException extends RuntimeException {

    public FieldException() {
        super();
    }

    public FieldException(String message) {
        super(message);
    }

    public FieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldException(Throwable cause) {
        super(cause);
    }

    protected FieldException(String message, Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
