package ru.solomka.authentication.mediatr;

public interface Mediatr {
    <O, R> R dispatch(O query, Class<R> returnedObjectClass);
}