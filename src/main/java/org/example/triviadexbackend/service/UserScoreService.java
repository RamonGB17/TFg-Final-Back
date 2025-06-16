package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.User;
import org.example.triviadexbackend.entity.UserScore;
import org.example.triviadexbackend.repository.UserScoreRepository;
import org.example.triviadexbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserScoreService {

    @Autowired
    private UserScoreRepository userScoreRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserScore> getAllUserScoresOrderByTotal() {
        return userScoreRepository.findTopByTotalScore();
    }

    @Transactional
    public void addTriviaPoints(Long userId, int points) {
        UserScore userScore = userScoreRepository.findById(userId)
                .orElseGet(() -> {
                    // Creamos y enlazamos el usuario manualmente
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + userId));
                    UserScore newScore = new UserScore();
                    newScore.setUserId(userId);
                    newScore.setUser(user);
                    newScore.setTriviaScore(0);
                    newScore.setDailyScore(0);
                    newScore.setTotalScore(0);
                    return newScore;
                });

        userScore.setTriviaScore(userScore.getTriviaScore() + points);
        userScore.setTotalScore(userScore.getTotalScore() + points);

        userScoreRepository.save(userScore); // No usamos merge ni saveAndFlush
    }

    @Transactional
    public void addDailyPoints(Long userId, int points) {
        UserScore userScore = userScoreRepository.findById(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + userId));
                    UserScore newScore = new UserScore();
                    newScore.setUserId(userId);
                    newScore.setUser(user);
                    newScore.setTriviaScore(0);
                    newScore.setDailyScore(0);
                    newScore.setTotalScore(0);
                    return newScore;
                });

        userScore.setDailyScore(userScore.getDailyScore() + points);
        userScore.setTotalScore(userScore.getTotalScore() + points);

        userScoreRepository.save(userScore);
    }

    public List<UserScore> getRanking() {
        return getAllUserScoresOrderByTotal();
    }
}
