package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.dto.UserScoreDTO;
import org.example.triviadexbackend.entity.User;
import org.example.triviadexbackend.entity.UserScore;
import org.example.triviadexbackend.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scores")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    @GetMapping("/ranking")
    public ResponseEntity<List<UserScoreDTO>> getRanking() {
        List<UserScore> scores = userScoreService.getRanking();
        List<UserScoreDTO> dtos = scores.stream().map(UserScoreDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ✅ Usuario logueado suma puntos de trivia
    @PostMapping("/trivia")
    public ResponseEntity<String> addTriviaPoints(@AuthenticationPrincipal User user,
                                                  @RequestBody Map<String, Integer> body) {
        if (user == null) return ResponseEntity.status(401).body("No autenticado");

        Integer points = body.get("points");
        if (points == null) return ResponseEntity.badRequest().body("Falta el campo 'points'");

        userScoreService.addTriviaPoints(user.getId(), points);
        return ResponseEntity.ok("Puntos de trivia añadidos correctamente");
    }

    // ✅ Usuario logueado suma puntos de actividad diaria
    @PostMapping("/daily")
    public ResponseEntity<String> addDailyPoints(@AuthenticationPrincipal User user,
                                                 @RequestBody Map<String, Integer> body) {
        if (user == null) return ResponseEntity.status(401).body("No autenticado");

        Integer points = body.get("points");
        if (points == null) return ResponseEntity.badRequest().body("Falta el campo 'points'");

        userScoreService.addDailyPoints(user.getId(), points);
        return ResponseEntity.ok("Puntos de actividad diaria añadidos correctamente");
    }

    // 🛡️ ADMIN → Asignar puntos a cualquier usuario
    @PostMapping("/admin/trivia")
    public ResponseEntity<Void> addTriviaPointsAdmin(@RequestParam Long userId, @RequestParam int points) {
        userScoreService.addTriviaPoints(userId, points);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/daily")
    public ResponseEntity<Void> addDailyPointsAdmin(@RequestParam Long userId, @RequestParam int points) {
        userScoreService.addDailyPoints(userId, points);
        return ResponseEntity.ok().build();
    }
}
