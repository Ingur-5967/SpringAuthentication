package ru.solomka.authentication.cqrs.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CreateUserCommand {
    @NonNull
    private String username;
    @NonNull
    private String password;
}