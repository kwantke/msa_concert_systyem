package com.kokk.concert.infrastructure.config.security;


import com.kokk.concert.application.port.out.UserRepositoryPort;
import com.kokk.concert.domain.model.entity.User;
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
