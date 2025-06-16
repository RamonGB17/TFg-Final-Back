package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByFranchiseId(Long franchiseId);
}
