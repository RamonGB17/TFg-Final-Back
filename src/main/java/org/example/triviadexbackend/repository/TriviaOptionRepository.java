package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.TriviaOption;
import org.example.triviadexbackend.entity.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TriviaOptionRepository extends JpaRepository<TriviaOption, Long> {
    List<TriviaOption> findByTrivia(Trivia trivia);
}
