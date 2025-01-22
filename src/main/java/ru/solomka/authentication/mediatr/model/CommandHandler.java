package ru.solomka.authentication.mediatr.model;

public interface CommandHandler<O, R> {
    R handle(O parameter);
}