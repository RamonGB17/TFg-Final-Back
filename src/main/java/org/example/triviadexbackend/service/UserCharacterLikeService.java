package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.UserCharacterLike;
import org.example.triviadexbackend.repository.UserCharacterLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCharacterLikeService {

    @Autowired
    private UserCharacterLikeRepository likeRepository;

    public void like(Long userId, Long characterId) {
        if (!likeRepository.existsByUserIdAndCharacterId(userId, characterId)) {
            UserCharacterLike like = new UserCharacterLike();
            like.setUserId(userId);
            like.setCharacterId(characterId);
            likeRepository.save(like);
        }
    }

    @Transactional
    public void unlike(Long userId, Long characterId) {
        likeRepository.deleteByUserIdAndCharacterId(userId, characterId);
    }

    public boolean hasLiked(Long userId, Long characterId) {
        return likeRepository.existsByUserIdAndCharacterId(userId, characterId);
    }

    public long countLikes(Long characterId) {
        return likeRepository.countByCharacterId(characterId);
    }

    public List<UserCharacterLike> getAllLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId);
    }
}
