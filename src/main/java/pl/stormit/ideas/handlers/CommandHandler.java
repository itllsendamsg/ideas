package pl.stormit.ideas.handlers;

import pl.stormit.ideas.input.UserInputCommand;

public interface CommandHandler {

    void handle(UserInputCommand command);

    boolean supports(String name);

}
