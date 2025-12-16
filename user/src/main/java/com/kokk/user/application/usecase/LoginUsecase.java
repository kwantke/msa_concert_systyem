package com.kokk.user.application.usecase;

import com.kokk.user.application.common.UseCase;
import com.kokk.user.application.dto.request.LoginRequestDto;
import com.kokk.user.application.dto.response.LoginResponseDto;
import com.kokk.user.application.port.in.UserServicePort;
import com.kokk.user.application.service.TokenService;
import com.kokk.user.domain.model.entity.User;
import com.kokk.user.infrastructure.config.cookie.CookieSupport;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
@UseCase
public class LoginUsecase {

  private final UserServicePort userServicePort;
  private final TokenService tokenService;
  private final CookieSupport cookieSupport;

  public LoginResponseDto login(LoginRequestDto req, HttpServletResponse res) throws Exception {

    User user = userServicePort.login(req);
    TokenService.TokenPair pair = tokenService.issueToken(user);

    cookieSupport.writeRefreshCookie(res, pair.refreshToken());
    return LoginResponseDto.from(user, pair);
  }


}
