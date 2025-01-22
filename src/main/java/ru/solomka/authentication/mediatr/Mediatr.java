package ru.solomka.authentication.mediatr;

public interface Mediatr {
    <O, R> R dispach(O query, Class<R> returnedObjectClass);
}