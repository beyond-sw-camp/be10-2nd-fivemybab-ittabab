package com.fivemybab.ittabab.security.util;

import com.fivemybab.ittabab.user.command.application.service.UserCommandService;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final UserCommandService userCommandService;

    public JwtUtil(
            @Value("${token.secret}") String secretKey,
            UserCommandService userCommandService
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userCommandService = userCommandService;
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e);
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userCommandService.loadUserByUsername(this.getLoginId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getLoginId(String token) {
        return parseClaims(token).getSubject();
    }

    public String getUserId(String token) {
        Claims claims = parseClaims(token);
        return claims.get("userId", String.class); // "userId"를 클레임에서 추출
    }

    public UserRole getRole(String token) {
        Claims claims = parseClaims(token);  // JWT 클레임에서 정보를 추출
        List<String> roles = claims.get("auth", List.class);  // "auth" 클레임에서 권한 정보를 가져옴

        // 권한 정보 중 하나라도 ADMIN인 경우, ADMIN 역할로 간주
        if (roles.contains("ADMIN")) {
            return UserRole.ADMIN;
        } else {
            return UserRole.USER;  // 기본 권한을 USER로 설정
        }
    }
}