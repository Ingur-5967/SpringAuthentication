package ru.solomka.authentication.service.user;

import ru.solomka.authentication.repository.UserEntity;

public interface UserService {
    UserEntity getUserByUsername(String username);
}