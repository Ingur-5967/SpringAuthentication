package ru.solomka.authentication.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.solomka.authentication.api.request.AuthenticationRequest;
import ru.solomka.authentication.api.request.RegistrationRequest;
import ru.solomka.authentication.api.response.TokenResponse;
import ru.solomka.authentication.cqrs.command.CreateUserCommand;
import ru.solomka.authentication.mediatr.Mediatr;
import ru.solomka.authentication.service.authentication.AuthenticationService;

@RestController
@RequestMapping("/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Mediatr mediatr;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@Validated @RequestBody AuthenticationRequest authRequest) {
        String authorizedUserToken = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(authorizedUserToken));
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenResponse> registration(@Validated @RequestBody RegistrationRequest registrationRequest) {
        CreateUserCommand createUserCommand = new CreateUserCommand(
                registrationRequest.getUsername(),
                registrationRequest.getPassword()
        );

        String createdUserToken = mediatr.dispach(createUserCommand, String.class);

        return ResponseEntity.ok(new TokenResponse(createdUserToken));
    }
}
