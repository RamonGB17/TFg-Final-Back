package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.entity.UserCharacterLike;
import org.example.triviadexbackend.service.UserCharacterLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class UserCharacterLikeController {

    @Autowired
    private UserCharacterLikeService likeService;

    // POST → Dar like
    @PostMapping("/{characterId}")
    public ResponseEntity<Void> like(@PathVariable Long characterId, @AuthenticationPrincipal org.example.triviadexbackend.entity.User user) {
        likeService.like(user.getId(), characterId);
        return ResponseEntity.ok().build();
    }

    // DELETE → Quitar like
    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> unlike(@PathVariable Long characterId, @AuthenticationPrincipal org.example.triviadexbackend.entity.User user) {
        likeService.unlike(user.getId(), characterId);
        return ResponseEntity.noContent().build();
    }

    // GET → Saber si este usuario ha dado like
    @GetMapping("/{characterId}")
    public ResponseEntity<Boolean> hasLiked(@PathVariable Long characterId, @AuthenticationPrincipal org.example.triviadexbackend.entity.User user) {
        boolean liked = likeService.hasLiked(user.getId(), characterId);
        return ResponseEntity.ok(liked);
    }

    // GET → Contador de likes
    @GetMapping("/count/{characterId}")
    public ResponseEntity<Long> countLikes(@PathVariable Long characterId) {
        long count = likeService.countLikes(characterId);
        return ResponseEntity.ok(count);
    }

    // GET → Todos los personajes que le gustan a este usuario
    @GetMapping
    public ResponseEntity<List<UserCharacterLike>> getAllLikes(@AuthenticationPrincipal org.example.triviadexbackend.entity.User user) {
        return ResponseEntity.ok(likeService.getAllLikesByUser(user.getId()));
    }
}
