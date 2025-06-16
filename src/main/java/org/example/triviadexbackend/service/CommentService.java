package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.Character;
import org.example.triviadexbackend.entity.Comment;
import org.example.triviadexbackend.entity.User;
import org.example.triviadexbackend.repository.CharacterRepository;
import org.example.triviadexbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public Comment addComment(Long characterId, User user, String content) {
        Optional<Character> characterOpt = characterRepository.findById(characterId);
        if (characterOpt.isEmpty()) throw new IllegalArgumentException("Personaje no encontrado");

        Comment comment = new Comment();
        comment.setCharacter(characterOpt.get());
        comment.setUser(user);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForCharacter(Long characterId) {
        return commentRepository.findByCharacterIdOrderByCreatedAtDesc(characterId);
    }
}
