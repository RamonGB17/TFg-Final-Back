package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.dto.CommentDTO;
import org.example.triviadexbackend.entity.Comment;
import org.example.triviadexbackend.entity.User;
import org.example.triviadexbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // ✅ Añadir comentario (requiere login)
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestParam Long characterId,
                                                 @RequestBody String content,
                                                 @AuthenticationPrincipal User user) {
        if (user == null) return ResponseEntity.status(401).build();
        Comment comment = commentService.addComment(characterId, user, content);
        return ResponseEntity.ok(new CommentDTO(comment));
    }

    // 🔓 Obtener comentarios de un personaje
    @GetMapping("/character/{characterId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long characterId) {
        List<Comment> comments = commentService.getCommentsForCharacter(characterId);
        List<CommentDTO> dtos = comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
