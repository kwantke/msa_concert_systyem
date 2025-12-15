package com.kokk.user.application.dto.response;

import com.kokk.user.domain.model.entity.User;

public record JoinResponseDto(
        String userId,
        String name,
        String email
) {
  public static JoinResponseDto from(User userEntity) {
    return new JoinResponseDto(
            userEntity.getUserId(),
            userEntity.getName(),
            userEntity.getEmail()
    );
  }
}
