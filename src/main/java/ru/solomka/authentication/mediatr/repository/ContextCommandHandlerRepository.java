package ru.solomka.authentication.mediatr.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import ru.solomka.authentication.mediatr.model.CommandHandler;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContextCommandHandlerRepository implements CommandHandlerRepository {

    private final ApplicationContext context;

    @Override
    @SuppressWarnings("unchecked")
    public <O, R> Optional<CommandHandler<O, R>> findCommandHandler(Class<O> queryClass, Class<R> returnedObjectClass) {
        ResolvableType handler = ResolvableType.forClassWithGenerics(CommandHandler.class, queryClass, returnedObjectClass);

        if(context.getBeanNamesForType(handler).length > 1)
            throw new IllegalArgumentException("Multiple command handler found for '%s'".formatted(queryClass));

        return Optional.ofNullable((CommandHandler<O, R>) context.getBeanProvider(handler).getIfAvailable());
    }
}
