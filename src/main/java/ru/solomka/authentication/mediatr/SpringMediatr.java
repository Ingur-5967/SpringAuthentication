package ru.solomka.authentication.mediatr;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.solomka.authentication.mediatr.model.CommandHandler;
import ru.solomka.authentication.mediatr.service.CommandHandlerService;

@Component
@RequiredArgsConstructor
public class SpringMediatr implements Mediatr {

    private final CommandHandlerService commandService;

    @Override
    @SuppressWarnings("unchecked")
    public <O, R> R dispach(O query, Class<R> returnedObjectClass) {
        CommandHandler<O, R> handler =
                (CommandHandler<O, R>) commandService.getCommandHandlerByArgumentAndResponseTypes(query.getClass(), returnedObjectClass);

        return handler.handle(query);
    }
}
