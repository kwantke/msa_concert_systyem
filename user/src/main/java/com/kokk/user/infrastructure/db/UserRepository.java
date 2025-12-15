package com.kokk.user.infrastructure.db;

import com.kokk.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
