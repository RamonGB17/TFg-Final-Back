package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.UserTriviaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTriviaAnswerRepository extends JpaRepository<UserTriviaAnswer, Long> {

    List<UserTriviaAnswer> findByUserId(Long userId);
}
