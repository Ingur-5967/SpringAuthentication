package ru.solomka.authentication.mapper.schema;

import org.springframework.stereotype.Component;
import ru.solomka.authentication.api.User;
import ru.solomka.authentication.mapper.SchemaMapping;
import ru.solomka.authentication.repository.UserEntity;

@Component
public class UserEntityUserSchemaMapping implements SchemaMapping<UserEntity, User> {

    @Override
    public User map(UserEntity parameter) {
        return User.builder()
                .username(parameter.getUsername())
                .build();
    }
}