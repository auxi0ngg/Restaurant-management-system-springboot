package com.auxiongg.restaurant.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

import javax.crypto.SecretKey;

import com.auxiongg.restaurant.users.Role;

import java.util.Date;

@AllArgsConstructor
public class Jwt {
    private Claims claims;
    private SecretKey key;

    public Long getUserId() {
        return Long.valueOf(claims.getSubject());
    }

    public Role getRole() {
        return Role.valueOf(claims.get("role", String.class));
    }

    public Integer getTokenExpiration() {
        return Integer.valueOf(String.valueOf(claims.get("exp", Long.class)));
    }

    public boolean isExpired() {
        try {
            return claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return Jwts.builder().claims(claims).signWith(key).compact();
    }

}
