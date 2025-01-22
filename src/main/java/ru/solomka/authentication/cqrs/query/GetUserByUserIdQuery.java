package ru.solomka.authentication.cqrs.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GetUserByUserIdQuery {
    @NonNull
    private Long userId;
}