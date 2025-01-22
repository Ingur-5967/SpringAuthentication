package ru.solomka.authentication.mapper.schema;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.solomka.authentication.mapper.SchemaMapping;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.service.user.details.UserDetailsImpl;

@Component
public class UserEntityUserDetailsSchemaMapping implements SchemaMapping<UserEntity, UserDetails> {

    @Override
    public UserDetails map(UserEntity parameter) {
        return UserDetailsImpl.builder()
                .username(parameter.getUsername())
                .password(parameter.getPassword())
                .role(parameter.getRole())
                .build();
    }
}