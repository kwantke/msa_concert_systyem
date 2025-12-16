package com.kokk.concert.infrastructure.web.filter;


import com.kokk.concert.infrastructure.config.jwt.JwtProvider;
import com.kokk.concert.infrastructure.config.security.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final String BEARER = "Bearer ";

  private final JwtProvider jwtProvider;
  private final AuthService authService;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.equals("/auth/login")
            || path.equals("/auth/join")
            ;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authorization == null || !authorization.startsWith(BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = authorization.substring(BEARER.length());

    try {

      Jws<Claims> jws = jwtProvider.parse(token);
      Claims c = jws.getBody();

      // 토큰 만료 여부 확인
      if (jwtProvider.isExpired(c)) {
        throw new ExpiredJwtException(jws.getHeader(), c, "Access token expired");
      }

      final String accessJti = jwtProvider.getJti(c);

      final String userId = jwtProvider.getUserId(c);

      UserDetails userDetails = authService.loadUserByUserId(userId);

      UsernamePasswordAuthenticationToken auth =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);


    } catch (JwtException e) {
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);

  }
}
