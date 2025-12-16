package com.kokk.user.application.service;

import com.kokk.user.domain.model.entity.User;
import com.kokk.user.infrastructure.config.jwt.JwtProvider;
import com.kokk.user.infrastructure.db.redis.TokenStore;
import com.kokk.user.infrastructure.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final JwtProvider jwtProvider;
  private final TokenStore tokenStore;
  private final JwtProperties props;

  public TokenPair issueToken(User user) {
    String accessToken = jwtProvider.generateAccessToken(user);
    String rti = UUID.randomUUID().toString(); // Refresh Token Id
    String refreshToken = jwtProvider.generateRefreshToken(user, rti);

    // Redis 저장 (TTL = refresh-exp)
    Duration rttl = Duration.ofDays(props.getRefreshExpDays());
    tokenStore.saveRefreshToken(user.getUserId(), refreshToken, rttl);

    return new TokenPair(accessToken, refreshToken);


  }

  public record TokenPair(String accessToken, String refreshToken) {
  }


}
