package ru.solomka.authentication.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.solomka.authentication.service.jwt.tag.ClaimsService;
import ru.solomka.authentication.service.jwt.tag.TokenFactory;
import ru.solomka.authentication.service.jwt.tag.TokenValidator;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService implements ClaimsService, TokenValidator, TokenFactory {

    @Value("${authentication.backend.jwtExpirationMs}")
    private long lifetime;

    @Value("${authentication.backend.jwtSecret}")
    private String secret;

    @Override
    public String createToken(String subject) {
        return Jwts.builder().subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + lifetime))
                .signWith(signKey())
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return this.extractClaims(token).getSubject();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(signKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);

            boolean expired = claims.getExpiration()
                    .before(new Date(System.currentTimeMillis()));
            return !expired;
        } catch (JwtException ignored) {
            return false;
        }
    }

    @NonNull
    private SecretKey signKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}