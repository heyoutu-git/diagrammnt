package com.diagrammnt.exception;

public enum ErrorCode {
    IMAGE_NOT_FOUND(404, "图片不存在"),
    IMAGE_FILE_EMPTY(400, "文件为空"),
    IMAGE_FILE_TOO_LARGE(400, "文件过大"),
    IMAGE_FORMAT_UNSUPPORTED(400, "不支持的文件格式"),
    DUPLICATE_IMAGE(409, "图片已存在"),
    INTERNAL_ERROR(500, "系统内部错误");
    private final int code;
    private final String message;
    ErrorCode(int code, String message) { this.code = code; this.message = message; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
