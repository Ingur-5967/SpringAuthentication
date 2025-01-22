package ru.solomka.authentication.service.authentication;

public interface AuthenticationService {
    String authenticate(String username, String password);
}