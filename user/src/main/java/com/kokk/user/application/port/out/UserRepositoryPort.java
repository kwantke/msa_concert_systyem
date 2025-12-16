package com.kokk.user.application.port.out;

import com.kokk.user.domain.model.entity.User;

public interface UserRepositoryPort {

  User save(User user);

  User findByUserId(String userId);
}
