package ru.solomka.authentication.cqrs.handler.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.solomka.authentication.cqrs.command.CreateUserCommand;
import ru.solomka.authentication.mediatr.model.CommandHandler;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.repository.UserRepository;
import ru.solomka.authentication.service.jwt.tag.TokenFactory;
import ru.solomka.authentication.service.user.details.enums.UserRole;

@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, String> {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenFactory tokenFactory;

    @Override
    public String handle(CreateUserCommand parameter) {
        userRepository.findByUsername(parameter.getUsername())
                .ifPresent(action -> {
                    throw new RuntimeException("Username with username '%s' already exists".formatted(action.getUsername()));
                });

        UserEntity createdUser = UserEntity.builder()
                .username(parameter.getUsername())
                .password(encoder.encode(parameter.getPassword()))
                .role(UserRole.ROLE_USER)
                .build();

        userRepository.save(createdUser);

        return tokenFactory.createToken(createdUser.getUsername());
    }
}