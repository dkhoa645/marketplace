package com.dkhoa.marketplace.exception;

import com.dkhoa.marketplace.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
}
