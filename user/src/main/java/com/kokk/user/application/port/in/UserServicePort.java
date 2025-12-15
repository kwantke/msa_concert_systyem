package com.kokk.user.application.port.in;

import com.kokk.user.application.dto.request.JoinRequestDto;
import com.kokk.user.domain.model.entity.User;

public interface UserServicePort {

  User join(JoinRequestDto req);
}
