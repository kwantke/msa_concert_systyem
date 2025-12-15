package com.kokk.user.infrastructure.adapter;

import com.kokk.user.application.port.out.UserRepositoryPort;
import com.kokk.user.domain.model.entity.User;
import com.kokk.user.infrastructure.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserRepository userRepository;
  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
}
