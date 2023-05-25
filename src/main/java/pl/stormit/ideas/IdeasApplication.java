package pl.stormit.ideas;

import pl.stormit.ideas.handlers.*;
import pl.stormit.ideas.input.UserInputCommand;
import pl.stormit.ideas.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdeasApplication {

    private static Logger LOG = Logger.getLogger(IdeasApplication.class.getName());

    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private void start() {

        LOG.info("\n" +
                "   \n" +
                "   \n" +
                "                                           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡇⠀⠀⠀⠀⠀⠀⠀     \n" +
                "                             ⠀⠀⠀⠀⠀          ⠀⠀⠀⢀⣤⣾⠟⠀⣀⣠⠄⠀⠀⠀⠀      \n" +
                "                                     ⠀⠀⠀⠀⠀⠀⢠⣶⣿⠟⠁⢠⣾⠋⠁⠀⠀⠀⠀⠀        \n" +
                "                             ⠀⠀⠀        ⠀⠀⠀⠹⣿⡇⠀⠀⠸⣿⡄⠀⠀⠀⠀⠀⠀         \n" +
                "                                      ⠀⠀ ⠀⠀⠀⠀⠙⠷⡀⠀⠀⢹⠗⠀⠀⠀⠀⠀⠀        \n" +
                "                                   \t ⠀⠀⢀⣤⣴⡖⠒⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠒⢶⣄       \n" +
                "                                      ⠀⠀⠈⠙⢛⣻⠿⠿⠿⠟⠛⠛⠛⠋⠉⠀⠀⠀⣸⡿      \n" +
                "                             ⠀⠀         ⠀⠛⠿⣷⣶⣶⣶⣶⣾⠿⠗⠂⠀⢀⠴⠛⠁       \n" +
                "                                    ⠀⠀⠀⠀⠀  ⢰⣿⣦⣤⣤⣤⣴⣶⣶⠄⠀⠀⠀⠀⠀       \n" +
                "                                       ⣀⣤⡤⠄⠀⠀⠈⠉⠉⠉⠉⠉⠀⠀⠀⠀⢀⡀⠀⠀      \n" +
                "                                     ⠻⣿⣦⣄⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⣴⠾⠃⠀⢀       \n" +
                "                                      ⠀ ⠀⠈⠉⠛⠛⠛⠛⠛⠛⠛⠛⠋⠉⠁⠀⣀⣤⡶⠋     \n" +
                "                                        ⠀⠀⠀⠐⠒⠀⠠⠤⠤⠤⠶⠶⠚⠛⠛⠉⠀⠀⠀       \n" +
                "   \n" +
                "   \n" +
                "        -----------------------------------------------------------------------------------\n" +
                "        ***********************************************************************************\n" +
                "        -----------------------------------------------------------------------------------\n" +
                "\n" + "                                          [ GREETINGS ]" +
                "\n                     ENHKX Med+) ) ) Drone has received the transmission confirming" +
                "\n                                 that all processes are in full operation." +
                "\n"  +
                "\n");




        boolean applicationLoop = true;

        UserInputManager userInputManager = new UserInputManager();

        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHandler());
        handlers.add(new QuitCommandHandler());
        handlers.add(new CategoryCommandHandler());
        handlers.add(new QuestionCommandHandler());
        handlers.add(new AnswerCommandHandler());

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();

                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                for (CommandHandler handler : handlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                        break;
                    }
                }
                currentHandler

                        .orElseThrow(() -> new IllegalArgumentException("\n" +
                                "\n"+
                                "       ----------------------------------------------------------------------------------- \n" +
                                "       *********************************************************************************** \n" +
                                "       ----------------------------------------------------------------------------------- \n" +
                                "                         ENHKX Med+) ) ) Drone------------analysing------------            \n" +
                                "\n" + "                    [ ILLEGAL ARGUMENT EXCEPTION: UNKNOWN HANDLER ]" +
                                "\n" + "                  ENHKX Med+) ) ) Drone has analysed the provided input" +
                                "\n" + "                        and classifies the [ unknown handler ] as: " + userInputCommand.getCommand()))
                                .handle(userInputCommand);


            } catch (QuitIdeasApplicationException e) {

                LOG.info("Quit");
                applicationLoop = false;

            } catch (IllegalArgumentException e) {

                LOG.log(Level.WARNING, "\n" +
                        "\n"+
                        "       ----------------------------------------------------------------------------------- \n" +
                        "       *********************************************************************************** \n" +
                        "       ----------------------------------------------------------------------------------- \n" +
                        "                         ENHKX Med+) ) ) Drone------------analysing------------            \n" +
                        "\n" + "                              [ WARNING: VALIDATION EXCEPTION ] " +
                        "\n" + "                     ENHKX Med+) ) ) Drone has analysed the provided input" +
                        "\n" + "                           and determines that [ it cannot be validated ] " + e.getMessage());

            } catch (Exception e) {

                LOG.log(Level.SEVERE, "\n" +
                        "\n"+
                        "       ----------------------------------------------------------------------------------- \n" +
                        "       *********************************************************************************** \n" +
                        "       ----------------------------------------------------------------------------------- \n" +
                        "                         ENHKX Med+) ) ) Drone------------analysing------------            \n" +
                        "\n" + "                            [ SEVERE EXCEPTION: UNKNOWN ERROR ] " +
                        "\n" + "                 ENHKX Med+) ) ) Drone has analysed the provided input" +
                        "\n" + "                    and observes that  the [ unknown error ] occurred", e);
            }
        }
    }
}