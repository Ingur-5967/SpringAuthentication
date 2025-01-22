package ru.solomka.authentication.mediatr.service;

import ru.solomka.authentication.mediatr.model.CommandHandler;

public interface CommandHandlerService {
   <O, R> CommandHandler<O, R> getCommandHandlerByArgumentAndResponseTypes(Class<O> argumentType, Class<R> responseType);
}