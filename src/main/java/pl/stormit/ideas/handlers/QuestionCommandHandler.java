package pl.stormit.ideas.handlers;

import pl.stormit.ideas.dao.CategoryDao;
import pl.stormit.ideas.dao.QuestionDao;
import pl.stormit.ideas.input.UserInputCommand;
import pl.stormit.ideas.model.Category;
import pl.stormit.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(QuestionCommandHandler.class.getName());
    private static final String COMMAND_NAME = "question";

    private QuestionDao questionDao;
    private CategoryDao categoryDao;
    public QuestionCommandHandler(){
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
                    "\n" + "                                    [ ILLEGAL ARGUMENT EXCEPTION ]" +
                    "\n" + "                          ENHKX Med+) ) ) Drone has analysed the provided input" +
                    "\n" + "                              and indicates that the [ action cannot be null ]");
        }


        switch (command.getAction()) {
            case LIST:

                LOG.info("List of questions");

                if (!command.getParam().isEmpty()){

                    throw new IllegalArgumentException("\n" +
                            "\n" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "        ***********************************************************************************" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "                         ENHKX Med+) ) ) Drone------------analysing------------            " +
                            "\n" +
                            "\n" + "                                   [ ILLEGAL ARGUMENT EXCEPTION ]" +
                            "\n" + "                          ENHKX Med+) ) ) Drone has analysed the provided input" +
                            "\n" + "                                    and indicates that the category list" +
                            "\n" + "                                 does not support [ any additional parameters ]" +
                            "\n" + "                                    Please, check help for more information.");
                }

                List<Question> questions = questionDao.findAll();

                questions.forEach(System.out::println);
                break;

            case ADD:

                LOG.info("Add a question");

                if(command.getParam().size()!=2){

                      throw new IllegalArgumentException("\n" +
                              "\n" +
                              "\n" + "        -----------------------------------------------------------------------------------" +
                              "\n" + "        ***********************************************************************************" +
                              "\n" + "        -----------------------------------------------------------------------------------" +
                              "\n" + "                         ENHKX Med+) ) ) Drone------------analysing------------            " +
                              "\n" +
                              "\n" + "                                      [ ILLEGAL ARGUMENT EXCEPTION ]" +
                              "\n" + "                             ENHKX Med+) ) ) Drone has analysed the provided input" +
                              "\n" + "                                     and detects the [ wrong command format ]" +
                              "\n" + "                                       Please, check help for more information.");
                }

                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);

                Category category = categoryDao.findOne(categoryName)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryName));

                questionDao.add(new Question(questionName, category));
                break;

            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
