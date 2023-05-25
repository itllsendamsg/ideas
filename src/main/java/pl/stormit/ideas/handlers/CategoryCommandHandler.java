package pl.stormit.ideas.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.stormit.ideas.dao.CategoryDao;
import pl.stormit.ideas.dao.QuestionDao;
import pl.stormit.ideas.input.UserInputCommand;
import pl.stormit.ideas.model.Category;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(CategoryCommandHandler.class.getName());
    private static final String COMMAND_NAME = "category";
    private ObjectMapper objectMapper;

    private CategoryDao categoryDao;

    public CategoryCommandHandler(){
        categoryDao = new CategoryDao();
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
                    "\n" + "                       ENHKX Med+) ) ) Drone------------analysing------------              " +
                    "\n" +
                    "\n" + "                                    [ ILLEGAL ARGUMENT EXCEPTION ]" +
                    "\n" + "                           ENHKX Med+) ) ) Drone has analysed the provided input" +
                    "\n" + "                                and indicates that the [ action cannot be null ]");
        }

        switch (command.getAction()) {
            case LIST:

                LOG.info("List of categories...");

                if (!command.getParam().isEmpty()){

                      throw new IllegalArgumentException("\n" +
                              "\n" +
                              "\n" + "        -----------------------------------------------------------------------------------" +
                              "\n" + "        ***********************************************************************************" +
                              "\n" + "        -----------------------------------------------------------------------------------" +
                              "\n" + "                       ENHKX Med+) ) ) Drone------------analysing------------              " +
                              "\n" +
                              "\n" + "                                         [ ILLEGAL ARGUMENT EXCEPTION ]" +
                              "\n" + "                             ENHKX Med+) ) ) Drone has analysed the provided input" +
                              "\n" + "          and indicates that the [ category list does not support any additional parameters ]");
                }

                List<Category> categories = categoryDao.findAll();
                categories.forEach(System.out::println);
                break;

            case ADD:

                  LOG.info("\n" +
                          "\n"+
                          "\n"+ "  -----------------------------------------------------------------------------------" +
                          "\n"+ "  ***********************************************************************************" +
                          "\n"+ "  -----------------------------------------------------------------------------------" +
                          "\n"+ "                  ENHKX Med+) ) ) Drone------------analysing------------             " +
                          "\n" + "                                         [ INFORMATIVE ]" +
                          "\n" + "                     ENHKX Med+) ) ) Drone has analysed the provided input" +
                          "\n" + "                           and observes that [ a category is being added  ] ");

                if(command.getParam().size()!=1){

                    throw new IllegalArgumentException("\n" +
                            "\n" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "        ***********************************************************************************" +
                            "\n" + "        -----------------------------------------------------------------------------------" +
                            "\n" + "                        ENHKX Med+) ) ) Drone------------analysing------------             " +
                            "\n" +
                            "\n" + "                                  [ ILLEGAL ARGUMENT EXCEPTION ]" +
                            "\n" + "                           ENHKX Med+) ) ) Drone has analysed the provided input" +
                            "\n" + "                                and observes the [ wrong command format ]" +
                            "\n" + "                                Please, check help for more information.");
                }

                String categoryName = command.getParam().get(0);
                categoryDao.add(new Category(categoryName));
                break;

            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
