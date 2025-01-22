package ru.solomka.authentication.service.jwt.tag;

public interface TokenFactory {
    String createToken(String subject);
}