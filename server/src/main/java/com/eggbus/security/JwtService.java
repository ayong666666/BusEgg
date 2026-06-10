package com.eggbus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final SecretKey secretKey;
  private final Long expireHours;

  public JwtService(
      @Value("${busegg.jwt.secret}") String secret,
      @Value("${busegg.jwt.expire-hours}") Long expireHours
  ) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.expireHours = expireHours;
  }

  public String generateToken(Long userId, String username) {
    Instant now = Instant.now();
    return Jwts.builder()
      .subject(username)
      .claim("userId", userId)
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(expireHours, ChronoUnit.HOURS)))
      .signWith(secretKey)
      .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parser()
      .verifyWith(secretKey)
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }
}

