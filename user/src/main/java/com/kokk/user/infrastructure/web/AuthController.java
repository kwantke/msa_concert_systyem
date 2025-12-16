package com.kokk.user.infrastructure.web;


import com.kokk.user.application.dto.request.JoinRequestDto;
import com.kokk.user.application.dto.request.LoginRequestDto;
import com.kokk.user.application.dto.response.JoinResponseDto;
import com.kokk.user.application.dto.response.LoginResponseDto;
import com.kokk.user.application.usecase.JoinUsecase;
import com.kokk.user.application.usecase.LoginUsecase;
import com.kokk.user.infrastructure.config.cookie.CookieSupport;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JoinUsecase joinUsecase;
  private final LoginUsecase loginUsecase;
  private final CookieSupport cookieSupport;

  @PostMapping("/join")
  public ResponseEntity<JoinResponseDto> join(@RequestBody JoinRequestDto req) {
    JoinResponseDto result = joinUsecase.join(req);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto req, HttpServletResponse res) throws Exception {
    LoginResponseDto result = loginUsecase.login(req,res);

    return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + result.accessToken())
            .body(result);
  }
}
