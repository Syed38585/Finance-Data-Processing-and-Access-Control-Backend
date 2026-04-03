package com.zorvyn.project.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

        @Value("${jwt.secret}")
        private String secret;

        private Key getKey() {
            return Keys.hmacShaKeyFor(secret.getBytes());
        }

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

        // 🔹 Extract Username
        public String extractUsername(String token) {
            return getClaims(token).getSubject();
        }

        // 🔹 Validate
        public boolean validateToken(String token, String username) {
            return extractUsername(token).equals(username) && !isExpired(token);
        }

        private boolean isExpired(String token) {
            return getClaims(token).getExpiration().before(new Date());
        }

        private Claims getClaims(String token) {
            return Jwts.parserBuilder()

                    .setSigningKey(getKey()) // ✅ SAME KEY AGAIN
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
}
