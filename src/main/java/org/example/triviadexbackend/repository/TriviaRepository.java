package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.Trivia;
import org.example.triviadexbackend.entity.TriviaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TriviaRepository extends JpaRepository<Trivia, Long> {

    @Query("SELECT t FROM Trivia t WHERE t.type = :type ORDER BY function('RANDOM')")
    List<Trivia> findByType(@Param("type") TriviaType type);
}
