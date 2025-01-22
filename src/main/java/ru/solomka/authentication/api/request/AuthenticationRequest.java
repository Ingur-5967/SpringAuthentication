package ru.solomka.authentication.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private final String username;
    private final String password;
}