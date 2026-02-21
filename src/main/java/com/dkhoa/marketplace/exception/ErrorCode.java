package com.dkhoa.marketplace.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

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
