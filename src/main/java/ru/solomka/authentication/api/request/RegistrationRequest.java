package ru.solomka.authentication.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}