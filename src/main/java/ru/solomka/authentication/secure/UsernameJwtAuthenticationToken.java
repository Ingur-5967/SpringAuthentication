package ru.solomka.authentication.secure;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UsernameJwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;

    public UsernameJwtAuthenticationToken(UserDetails principal) {
        super(principal.getAuthorities());
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
