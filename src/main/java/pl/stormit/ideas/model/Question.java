package pl.stormit.ideas.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String name;
    private Category category;
    private List<Answer> answers;
    public Question() {
    }

    public Question(String name, Category category) {
        this.name = name;
        this.category = category;
        this.answers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", answers=" + answers +
                '}';
    }
}
