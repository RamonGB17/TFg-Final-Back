package org.example.triviadexbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_scores")
public class UserScore {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "trivia_score")
    private int triviaScore;

    @Column(name = "daily_score")
    private int dailyScore;

    @Version
    @Column(name = "version")
    private Integer version;

    // Getters y setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
