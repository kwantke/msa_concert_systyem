package com.kokk.user.application.dto.response;

import com.kokk.user.application.service.TokenService;
import com.kokk.user.domain.model.entity.User;

public record LoginResponseDto(
        String userId,
        String name,
        String accessToken

) {
  public static LoginResponseDto from(User user, TokenService.TokenPair pair) {
    return new LoginResponseDto(
            user.getUserId(),
            user.getName(),
            pair.accessToken()

    );
  }
}
