package com.kokk.user.application.port.in;

import com.kokk.user.application.dto.request.JoinRequestDto;
import com.kokk.user.application.dto.request.LoginRequestDto;
import com.kokk.user.domain.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserServicePort {

  User join(JoinRequestDto req);

  User loadUserByUserId(String userId);

  User login(LoginRequestDto req) throws Exception;
}
