package com.kokk.user.application.usecase;

import com.kokk.user.application.common.UseCase;
import com.kokk.user.application.dto.request.JoinRequestDto;
import com.kokk.user.application.dto.response.JoinResponseDto;
import com.kokk.user.application.port.in.UserServicePort;
import com.kokk.user.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@UseCase
public class JoinUsecase {

  private final UserServicePort userServicePort;

  public JoinResponseDto join(JoinRequestDto requestDto) {

    User userEntity = userServicePort.join(requestDto);

    return JoinResponseDto.from(userEntity);
  }
}
