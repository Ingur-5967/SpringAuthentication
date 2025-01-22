package ru.solomka.authentication.service.jwt.tag;

import io.jsonwebtoken.Claims;

public interface ClaimsService {
    String extractUsername(String token);
    Claims extractClaims(String token);
}