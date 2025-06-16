package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    @Query("SELECT us FROM UserScore us ORDER BY us.totalScore DESC")
    List<UserScore> findTopByTotalScore();

    @Query("SELECT us FROM UserScore us ORDER BY us.triviaScore DESC")
    List<UserScore> findTopByTriviaScore();

    @Query("SELECT us FROM UserScore us ORDER BY us.dailyScore DESC")
    List<UserScore> findTopByDailyScore();

    Optional<UserScore> findByUserId(Long userId);

    @Modifying
    @Query("UPDATE UserScore us SET us.totalScore = us.triviaScore + us.dailyScore WHERE us.userId = :userId")
    void updateTotalScore(Long userId);
}
