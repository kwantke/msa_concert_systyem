package com.kokk.user.infrastructure.config.cookie;


import com.kokk.user.infrastructure.properties.JwtProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class CookieSupport {

  private final JwtProperties props;

  public void writeRefreshCookie(HttpServletResponse res, String refresToken) {
    int maxAgeSec = (int) Duration.ofDays(props.getRefreshExpDays()).getSeconds();
    ResponseCookie cookie = ResponseCookie
            .from(props.getCookie().getRefreshName(), refresToken)
            .httpOnly(props.getCookie().isHttpOnly())
            .secure(props.getCookie().isSecure())
            //.domain(props.getCookie().getDomain())
            .sameSite(props.getCookie().getSameSite())
            .path("/")
            .maxAge(maxAgeSec)
            .build();

    res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  }
}
