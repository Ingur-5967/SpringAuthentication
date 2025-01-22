package ru.solomka.authentication.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.solomka.authentication.api.User;
import ru.solomka.authentication.cqrs.query.GetUserByUserIdQuery;
import ru.solomka.authentication.mapper.SchemaMapping;
import ru.solomka.authentication.mediatr.Mediatr;
import ru.solomka.authentication.repository.UserEntity;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class UserController {

    private final Mediatr mediatr;
    private final SchemaMapping<UserEntity, User> userMapping;

    @GetMapping
    public ResponseEntity<String> getContentForAuthorizedUsers() {
        return ResponseEntity.ok("This is the content for authorized users!");
    }

    @GetMapping(value = "/me")
    public ResponseEntity<User> getMyProfile(@RequestParam("id") Long userId) {
        GetUserByUserIdQuery userByIdQuery = new GetUserByUserIdQuery(userId);
        UserEntity userEntityById = mediatr.dispach(userByIdQuery, UserEntity.class);
        return ResponseEntity.ok(userMapping.map(userEntityById));
    }
}