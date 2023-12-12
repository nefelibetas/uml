package com.fish.universityeducationalsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    @Value("${jwt.secret}")
    String secret;
    @Value("${jwt.expireTime}")
    Long expireTime;
    static final String ISS = "Fish";
    static final String SUBJECT = "UML";
    static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
    public String createToken(String str) {
        String uuid = UUID.randomUUID().toString();
        Date expireDate = Date.from(Instant.now().plusSeconds(expireTime));
        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .claim("userId", str)
                .id(uuid)
                .subject(SUBJECT)
                .issuedAt(expireDate)
                .issuer(ISS)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), ALGORITHM)
                .compact();
    }
    public Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token);
    }
    public JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }
    public Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }
    public String getRedisKey(String token) {
        return parsePayload(token).get("userId", String.class);
    }
}
