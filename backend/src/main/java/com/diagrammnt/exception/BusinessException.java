package com.diagrammnt.exception;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String location;
    public BusinessException(ErrorCode errorCode, String detail, String location) {
        super(errorCode.getMessage() + ": " + detail);
        this.errorCode = errorCode;
        this.location = location;
    }
    public ErrorCode getErrorCode() { return errorCode; }
    public String getLocation() { return location; }
}
