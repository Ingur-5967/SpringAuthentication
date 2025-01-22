package ru.solomka.authentication.service.jwt.tag;

public interface TokenValidator {
    boolean validateToken(String token);
}