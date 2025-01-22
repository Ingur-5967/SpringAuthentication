package ru.solomka.authentication.cqrs.handler.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.solomka.authentication.cqrs.query.GetUserByUserIdQuery;
import ru.solomka.authentication.mediatr.model.CommandHandler;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class GetUserByUserIdQueryHandler implements CommandHandler<GetUserByUserIdQuery, UserEntity> {

    private final UserRepository userRepository;

    @Override
    public UserEntity handle(GetUserByUserIdQuery parameter) {
        return userRepository.findById(parameter.getUserId())
                .orElseThrow(() -> new RuntimeException("User with id '%s' not found".formatted(parameter.getUserId())));
    }
}