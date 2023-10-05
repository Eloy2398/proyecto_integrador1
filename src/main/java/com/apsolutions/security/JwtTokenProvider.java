package com.apsolutions.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

    private static final String ACCESS_TOKEN_SECRET = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    private String createToken(Map<String, Object> extra, UserDetails user) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        return Jwts
                .builder()
                .setClaims(extra)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaim(token, Claims::getExpiration).before(new Date());
    }

    public String createToken(UserDetails user) {
        return createToken(new HashMap<>(), user);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String getUsernameByToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = getUsernameByToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
