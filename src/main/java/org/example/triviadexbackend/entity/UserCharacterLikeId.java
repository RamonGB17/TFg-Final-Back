package org.example.triviadexbackend.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserCharacterLikeId implements Serializable {
    private Long userId;
    private Long characterId;

    public UserCharacterLikeId() {}

    public UserCharacterLikeId(Long userId, Long characterId) {
        this.userId = userId;
        this.characterId = characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCharacterLikeId that)) return false;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(characterId, that.characterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, characterId);
    }
}
