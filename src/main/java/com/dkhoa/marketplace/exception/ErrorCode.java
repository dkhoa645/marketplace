package com.dkhoa.marketplace.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNAUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "Unauthorized", HttpStatus.FORBIDDEN),
    RESOURCE_NOT_EXIST(1003, "Resource not exist", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1004, "User not found", HttpStatus.NOT_FOUND),
    USER_EXISTED(1005, "User already existed", HttpStatus.CONFLICT),
    USERNAME_INVALID(1006, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1007, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(1008, "Phone number invalid", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1008, "Email address invalid", HttpStatus.BAD_REQUEST),
    PROVINCE_EXISTED(1009, "Province already existed", HttpStatus.CONFLICT),
    PROVINCE_NOT_FOUND(1010, "Province not found", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(1011, "Email already existed", HttpStatus.CONFLICT),
    PHONE_EXISTED(1011, "Phone already existed", HttpStatus.CONFLICT),
    PROFILE_EXISTED(1012, "Profile already existed", HttpStatus.CONFLICT),
    PROFILE_NOT_FOUND(1013, "Profile not found", HttpStatus.NOT_FOUND),
    INVALID_FIELD(1014, "Invalid field", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
