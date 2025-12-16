package com.kokk.user.infrastructure.config.security;

import com.kokk.user.application.port.out.UserRepositoryPort;
import com.kokk.user.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepositoryPort userRepositoryPort;
  public AuthUserDetails loadUserByUserId(String userId) {
    User user = userRepositoryPort.findByUserId(userId);
    return AuthUserDetails.from(user);
  }

}
