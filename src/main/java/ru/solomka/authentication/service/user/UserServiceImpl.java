package ru.solomka.authentication.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with name '%s' not found".formatted(username)));
    }
}