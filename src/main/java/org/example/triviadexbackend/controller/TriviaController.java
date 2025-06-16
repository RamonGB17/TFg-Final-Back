package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.dto.TriviaDTO;
import org.example.triviadexbackend.service.TriviaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
@CrossOrigin // Permite peticiones desde el front (puedes ajustar si quieres)
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<TriviaDTO>> getTriviaByCategory(@PathVariable String category) {
        List<TriviaDTO> triviaList = triviaService.getRandomTriviaByCategory(category);
        return ResponseEntity.ok(triviaList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveTrivia(@RequestBody TriviaDTO triviaDTO) {
        triviaService.saveTrivia(triviaDTO);
        return ResponseEntity.ok("Trivia guardada correctamente.");
    }
}
