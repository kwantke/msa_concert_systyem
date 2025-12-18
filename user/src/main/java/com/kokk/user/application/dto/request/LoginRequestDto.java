package com.kokk.user.application.dto.request;

public record LoginRequestDto(
        String userId,
        String password
) {
}
