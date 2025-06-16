package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.UserCharacterLike;
import org.example.triviadexbackend.entity.UserCharacterLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCharacterLikeRepository extends JpaRepository<UserCharacterLike, UserCharacterLikeId> {
    boolean existsByUserIdAndCharacterId(Long userId, Long characterId);
    void deleteByUserIdAndCharacterId(Long userId, Long characterId);
    long countByCharacterId(Long characterId);
    List<UserCharacterLike> findByUserId(Long userId);
}
