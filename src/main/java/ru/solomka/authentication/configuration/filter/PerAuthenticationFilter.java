package ru.solomka.authentication.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.solomka.authentication.secure.UsernameJwtAuthenticationToken;
import ru.solomka.authentication.mapper.SchemaMapping;
import ru.solomka.authentication.repository.UserEntity;
import ru.solomka.authentication.service.jwt.tag.ClaimsService;
import ru.solomka.authentication.service.jwt.tag.TokenValidator;
import ru.solomka.authentication.service.user.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PerAuthenticationFilter extends OncePerRequestFilter {

    private final ClaimsService claimsService;
    private final TokenValidator tokenValidator;

    private final UserService userService;
    private final SchemaMapping<UserEntity, UserDetails> userMapping;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);
        if (!tokenValidator.validateToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = claimsService.extractUsername(jwt);
        UserDetails userDetails = userMapping.map(userService.getUserByUsername(username));

        if (userDetails == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernameJwtAuthenticationToken(userDetails);
        authentication.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}