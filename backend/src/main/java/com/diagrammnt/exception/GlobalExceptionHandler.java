package com.diagrammnt.exception;

import com.diagrammnt.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusiness(BusinessException e) {
        log.warn("[BIZ_ERR] {} location={}", e.getMessage(), e.getLocation());
        return ApiResponse.error(e.getErrorCode().getCode(), e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleOther(Exception e) {
        log.error("[SYS_ERR]", e);
        return ApiResponse.error(500, "系统错误: " + e.getMessage());
    }
}
