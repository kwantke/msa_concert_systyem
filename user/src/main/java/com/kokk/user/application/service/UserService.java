package com.kokk.user.application.service;

import com.kokk.user.application.dto.request.JoinRequestDto;
import com.kokk.user.application.port.in.UserServicePort;
import com.kokk.user.application.port.out.UserRepositoryPort;
import com.kokk.user.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepositoryPort;
  private final PasswordEncoder encoder;
  @Override
  public User join(JoinRequestDto req) {

    User user = User.of(req.userId(), req.name(), encoder.encode(req.password()), req.email());

    return userRepositoryPort.save(user);
  }
}
