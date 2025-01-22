package ru.solomka.authentication.service.user.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.solomka.authentication.mapper.SchemaMapping;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final SchemaMapping<UserEntity, UserDetails> userMapping;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity foundedUserByName = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name '%s' not found".formatted(username)));

        return userMapping.map(foundedUserByName);
    }
}
