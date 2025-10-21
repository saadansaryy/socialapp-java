package com.mecaps.socialApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final static long ACCESS_TOKEN_EXP= 1000 * 60 + 60;
    private final static String SECRET_KEY = "a1319ab6608da86a283a72d8292ed5400f1a5ba985c60853dbc163740909fa47";

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public String generateAccessToken(String email, String role){
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP))
                .signWith(getSecretKey())
                .compact();
    }

    // Extract email
    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token){
        return extractAllClaims(token).get("role", String.class);
    }

    // Validate token
    public boolean isTokenValid(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().after(new Date());
    }

    /*
    Purpose of this method:
      This method reads and verifies a JWT token and returns all the data inside it (called claims)
       — like email, roles, issue time, and expiration.
    */
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
