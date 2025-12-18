package com.kokk.concert.infrastructure.adapter;


import com.kokk.concert.application.port.out.UserRepositoryPort;
import com.kokk.concert.domain.model.entity.User;
import com.kokk.concert.infrastructure.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserRepository userRepository;
  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findByUserId(String userId) {
    return userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("not found"));
  }
}
