package ru.solomka.authentication.mapper;

public interface SchemaMapping<F, T> {
    T map(F parameter);
}