package com.kokk.concert.infrastructure.config.jwt;


import com.kokk.concert.domain.model.entity.User;
import com.kokk.concert.infrastructure.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {

  private final JwtProperties props;

  public static final String US_ID = "userId";
  public static final String US_NAME = "name";
  public static final String CLAIM_USER = "usr";

  public String generateAccessToken(User user) {
    Instant now = Instant.now();
    String jti = UUID.randomUUID().toString(); // 토큰 ID

    Map<String, Object> principal = new HashMap<>();
    principal.put(US_ID, user.getUserId());
    principal.put(US_NAME, user.getName());

    return Jwts.builder()
            .setId(jti)
            .setSubject(String.valueOf(user.getUserId()))
            .claim(CLAIM_USER, principal)
            .setIssuer(props.getIssuer())
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(props.getAccessExpMin(), ChronoUnit.MINUTES)))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
  }

  public String generateRefreshToken(User user, String refreshJti) {
    Instant now = Instant.now();

    Map<String, Object> principal = new HashMap<>();

    principal.put(US_ID, user.getUserId());
    principal.put(US_NAME, user.getName());

    return Jwts.builder()
            .setId(refreshJti)
            .setSubject(String.valueOf(user.getUserId()))
            .claim(CLAIM_USER, principal)
            .setIssuer(props.getIssuer())
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(props.getRefreshExpDays(), ChronoUnit.DAYS)))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
  }

  public Jws<Claims> parse(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
  }

  public boolean isExpired(Claims c) {
    Date exp = c.getExpiration();
    return exp != null && exp.before(new Date());
  }

  public String getJti(Claims c) {
    return c.getId();
  }

  public String getUserId(Claims c) {
    String sub = c.getSubject();
    if(sub == null) return null;

    return sub;
  }
}
