package ru.solomka.authentication.mediatr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solomka.authentication.mediatr.model.CommandHandler;
import ru.solomka.authentication.mediatr.repository.CommandHandlerRepository;

@Service
@RequiredArgsConstructor
public class CommandHandlerServiceImpl implements CommandHandlerService {

    private final CommandHandlerRepository commandRepository;

    @Override
    public <O, R> CommandHandler<O, R> getCommandHandlerByArgumentAndResponseTypes(Class<O> argumentType, Class<R> responseType) {
        return commandRepository.findCommandHandler(argumentType, responseType)
                .orElseThrow(() -> new RuntimeException("Not found command handler for '%s'".formatted(argumentType.getName())));
    }
}