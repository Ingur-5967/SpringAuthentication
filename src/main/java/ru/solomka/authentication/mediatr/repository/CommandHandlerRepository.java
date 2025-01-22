package ru.solomka.authentication.mediatr.repository;

import ru.solomka.authentication.mediatr.model.CommandHandler;

import java.util.Optional;

public interface CommandHandlerRepository {
    <O, R> Optional<CommandHandler<O, R>> findCommandHandler(Class<O> queryClass, Class<R> returnedObjectClass);
}