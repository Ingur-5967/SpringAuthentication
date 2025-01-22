package ru.solomka.authentication.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    @NonNull
    private final String username;
    @NonNull
    private final String password;
}