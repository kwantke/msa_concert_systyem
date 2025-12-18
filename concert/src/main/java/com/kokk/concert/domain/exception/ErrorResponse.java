package com.kokk.concert.domain.exception;

public record ErrorResponse(
        String errorCode,
        String message,
        int statusCode
) {
}
