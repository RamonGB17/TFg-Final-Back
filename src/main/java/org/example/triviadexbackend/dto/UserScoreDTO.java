package org.example.triviadexbackend.dto;

import org.example.triviadexbackend.entity.UserScore;

public class UserScoreDTO {

    private Long userId;
    private String username;
    private int triviaScore;
    private int dailyScore;
    private int totalScore;

    public UserScoreDTO() {
    }

    public UserScoreDTO(UserScore score) {
        this.userId = score.getUser() != null ? score.getUser().getId() : null;
        this.username = score.getUser() != null ? score.getUser().getUsername() : "Desconocido";
        this.triviaScore = score.getTriviaScore();
        this.dailyScore = score.getDailyScore();
        this.totalScore = score.getTotalScore();
    }

    // Getters y Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTriviaScore() {
        return triviaScore;
    }

    public void setTriviaScore(int triviaScore) {
        this.triviaScore = triviaScore;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
