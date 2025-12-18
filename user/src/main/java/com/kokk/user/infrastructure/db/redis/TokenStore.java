package com.kokk.user.infrastructure.db.redis;

import com.kokk.user.infrastructure.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenStore {

  private final StringRedisTemplate redis;

  private static final String RT_KEY = "rt:%s";


  private final JwtProvider props;

  public record TokenPair(String accessToken, String refreshToken) {
  }


  public void saveRefreshToken(String userId, String refreshToken, Duration ttl) {
    String key = RT_KEY.formatted(userId);
    redis.opsForValue().set(key,refreshToken,ttl); // key, value, ttl
  }

  public Optional<String> getRefreshToken(String userId) {
    String refreshToken = redis.opsForValue().get(RT_KEY.formatted(userId));

    return Optional.ofNullable(refreshToken);
  }
}
