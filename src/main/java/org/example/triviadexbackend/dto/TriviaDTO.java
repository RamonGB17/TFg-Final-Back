package org.example.triviadexbackend.dto;

import java.util.List;

public class TriviaDTO {

    private String question;
    private String correctAnswer;
    private String type;
    private List<String> options;

    public TriviaDTO() {
    }

    public TriviaDTO(String question, String correctAnswer, String type, List<String> options) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.type = type;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
