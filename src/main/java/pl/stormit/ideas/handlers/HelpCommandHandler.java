package pl.stormit.ideas.handlers;

import pl.stormit.ideas.input.UserInputCommand;

public class HelpCommandHandler extends BaseCommandHandler {


    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {
        System.out.println("Help");
        System.out.println("Please notice the allowed commands: [ help, quit, category, question, answer ]");
        System.out.println("Please follow the command pattern: [ <command> <action> <param1> <param2> ]");
        System.out.println("Please follow the example: [ category add CategoryName ]");
    }

    @Override
    public boolean supports(String name) {
        return COMMAND_NAME.equals(name);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
