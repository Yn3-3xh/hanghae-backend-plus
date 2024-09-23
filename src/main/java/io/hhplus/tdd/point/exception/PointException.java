package io.hhplus.tdd.point.exception;

public class PointException extends RuntimeException {
    public PointException(String message) {
        super(message);
    }

    public static PointException nullId() {
        return new PointException(PointErrorMessage.NULL_ID.getMessage());
    }

    public static PointException amountMustBeGreaterThanZero() {
        return new PointException(PointErrorMessage.NULL_ID.getMessage());
    }

    public static PointException exceedMaxPoint() {
        return new PointException(PointErrorMessage.EXCEED_MAX_POINT.getMessage());
    }
}
