package com.kokk.payment.domain.exception;

public record ErrorResponse(
        String errorCode,
        String message,
        int statusCode
) {
}
