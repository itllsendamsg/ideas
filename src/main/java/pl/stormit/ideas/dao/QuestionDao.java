package pl.stormit.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.stormit.ideas.model.Answer;
import pl.stormit.ideas.model.Category;
import pl.stormit.ideas.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionDao {
    private static Logger LOG = Logger.getLogger(QuestionDao.class.getName());
    private ObjectMapper objectMapper;
    public QuestionDao() {
        this.objectMapper = new ObjectMapper();
    }
    public List<Question> findAll() {
        return getQuestions();
    }


    private List<Question> getQuestions() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./questions.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {

             LOG.log(Level.WARNING, "Error on getQuestions", e);

            return new ArrayList<>();
        }
    }

    public void add(Question question) {
        try {
            List<Question> questions = getQuestions();
            questions.add(question);

            Files.writeString(Paths.get("./questions.txt"), objectMapper.writeValueAsString(questions));


        } catch (IOException e) {

            LOG.log(Level.WARNING, "Error on addQuestion",e);
        }
    }

        private void saveQuestions(List<Question> questions){
            try {
                Files.writeString(Paths.get("./questions.txt"), objectMapper.writeValueAsString(questions));
            } catch (IOException e) {

                LOG.log(Level.WARNING, "saveQuestions", e);
            }

        }

        public Optional<Question> findOne(String name) {
        return getQuestions().stream()

                .filter(c -> c.getName().equals(name))
                .findAny();
    }
    public void addAnswer(Question question, Answer answer){
        List<Question> questions = getQuestions();
        for (Question q : questions){
            if (Objects.equals(q.getName(), question.getName())){
                q.getAnswers().add(answer);
            }
        }
        saveQuestions(questions);
    }
}
