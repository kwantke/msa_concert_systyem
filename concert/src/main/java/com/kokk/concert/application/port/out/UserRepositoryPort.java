package com.kokk.concert.application.port.out;


import com.kokk.concert.domain.model.entity.User;

public interface UserRepositoryPort {

  User save(User user);

  User findByUserId(String userId);
}
