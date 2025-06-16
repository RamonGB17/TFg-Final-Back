package org.example.triviadexbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_character_likes")
@IdClass(UserCharacterLikeId.class)
public class UserCharacterLike {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "character_id")
    private Long characterId;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCharacterId() { return characterId; }
    public void setCharacterId(Long characterId) { this.characterId = characterId; }
}
