package pl.stormit.ideas.handlers;

import pl.stormit.ideas.dao.CategoryDao;
import pl.stormit.ideas.dao.QuestionDao;
import pl.stormit.ideas.input.UserInputCommand;
import pl.stormit.ideas.model.Answer;
import pl.stormit.ideas.model.Category;
import pl.stormit.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class AnswerCommandHandler extends BaseCommandHandler {


    private static Logger LOG = Logger.getLogger(AnswerCommandHandler.class.getName());
    private static final String COMMAND_NAME = "answer";

    private QuestionDao questionDao;
    private CategoryDao categoryDao;
    public AnswerCommandHandler(){
        categoryDao = new CategoryDao();
        questionDao = new QuestionDao();
    }
    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
    @Override
    public void handle(UserInputCommand command) {
        if (command.getAction()==null){

            throw new IllegalArgumentException("\n" +
                    "\n" +
                    "\n" + "        -----------------------------------------------------------------------------------" +
                    "\n" + "        ***********************************************************************************" +
                    "\n" + "        -----------------------------------------------------------------------------------" +
                    "\n" + "                         ENHKX Med+) ) ) Drone------------analysing------------            " +
                    "\n" +
                    "\n" + "                                       [ ILLEGAL ARGUMENT EXCEPTION ]" +
                    "\n" + "                             ENHKX Med+) ) ) Drone has analysed the provided input" +
                    "\n" + "                                 and indicates that the [ action cannot be null ]");
        }

        switch (command.getAction()) {
            case LIST:
                LOG.info("List of answers");

                if(command.getParam().size()!=1){

                    throw new IllegalArgumentException("\n" +
                            "\n" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "        ***********************************************************************************" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "                         ENHKX Med+) ) ) Drone------------analysing------------            " +
                            "\n" +
                            "\n" + "                                   [ ILLEGAL ARGUMENT EXCEPTION ]" +
                            "\n" + "                            ENHKX Med+) ) ) Drone has analysed the provided input" +
                            "\n" + "                                    and detects the [ wrong command format ]" +
                            "\n" + "                                    Please, check help for more information.");
                }
                String questionName = command.getParam().get(0);

                Question question = questionDao.findOne(questionName)

                        .orElseThrow(()-> new IllegalArgumentException("\n" +
                                "\n" +
                                "\n" + "        -----------------------------------------------------------------------------------" +
                                "\n" + "        ***********************************************************************************" +
                                "\n" + "        -----------------------------------------------------------------------------------" +
                                "\n" + "                        ENHKX Med+) ) ) Drone------------analysing------------             " +
                                "\n" +
                                "\n" + "                                [ ILLEGAL ARGUMENT EXCEPTION ]" +
                                "\n" + "                          ENHKX Med+) ) ) Drone has analysed the provided input" +
                                "\n\" +                          and determines that the [ question has not been found ] " +
                                "\n\" +                             Please, check help for more information." + questionName));
                diaplayQuestion(question);
                break;

            case ADD:

                LOG.info("Add an answer");
                if(command.getParam().size()!=2){

                    throw new IllegalArgumentException("\n" +
                            "\n" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "        ***********************************************************************************" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "                       ENHKX Med+) ) ) Drone------------analysing------------              " +
                            "\n" +
                            "\n" + "                                    [ ILLEGAL ARGUMENT EXCEPTION ]" +
                            "\n" + "                            ENHKX Med+) ) ) Drone has analysed the provided input" +
                            "\n" + "                                      and observes the [ wrong command format ]" +
                            "\n" + "                                    Please, check help for more information.");
                }

                questionName = command.getParam().get(0);
                String answerName = command.getParam().get(1);

                question = questionDao.findOne(questionName)

                        .orElseThrow(()-> new IllegalArgumentException("\n" +
                                "\n" +
                                "\n" + "        -----------------------------------------------------------------------------------" +
                                "\n" + "        ***********************************************************************************" +
                                "\n" + "        -----------------------------------------------------------------------------------" +
                                "\n" + "                        ENHKX Med+) ) ) Drone------------analysing------------             " +
                                "\n" +
                                "\n" + "                                  [ ILLEGAL ARGUMENT EXCEPTION ]" +
                                "\n" + "                        ENHKX Med+) ) ) Drone has analysed the provided input" +
                                "\n" + "                         and determines that the [ question has not been found ]" +
                                "\n" +  "                        Please, check help for more information." + questionName));

                questionDao.addAnswer(question, new Answer(answerName));

                break;

            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
    private void diaplayQuestion(Question question) {
        System.out.println(question.getName());
        question.getAnswers().forEach(System.out::println);
    }
}
