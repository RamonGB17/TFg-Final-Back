package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.entity.UserTriviaAnswer;
import org.example.triviadexbackend.service.UserTriviaAnswerService;
import org.example.triviadexbackend.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-trivia-answers")
@CrossOrigin
public class UserTriviaAnswerController {

    private final UserTriviaAnswerService service;

    @Autowired
    private UserScoreService userScoreService;

    public UserTriviaAnswerController(UserTriviaAnswerService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserTriviaAnswer> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<UserTriviaAnswer> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTriviaAnswer> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserTriviaAnswer create(@RequestBody UserTriviaAnswer answer) {
        UserTriviaAnswer saved = service.create(answer);

        // ✅ Si la respuesta es correcta, asignar puntos
        if (saved.isCorrect()) {
            int puntos = 10;
            userScoreService.addTriviaPoints(saved.getUser().getId(), puntos);
        }


        return saved;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
