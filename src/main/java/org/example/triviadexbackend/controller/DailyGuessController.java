package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.entity.Character;
import org.example.triviadexbackend.service.CharacterService;
import org.example.triviadexbackend.service.DailyGuessService;
import org.example.triviadexbackend.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/daily")
@CrossOrigin
public class DailyGuessController {

    private final DailyGuessService dailyGuessService;
    private final CharacterService characterService;

    @Autowired
    private UserScoreService userScoreService;

    public DailyGuessController(DailyGuessService dailyGuessService, CharacterService characterService) {
        this.dailyGuessService = dailyGuessService;
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCharacterOfTheDay() {
        Character hidden = dailyGuessService.getCharacterOfTheDay();
        Map<String, Object> response = new HashMap<>();
        response.put("id", hidden.getId());
        response.put("message", "Personaje del día obtenido.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guess")
    public ResponseEntity<Map<String, Object>> guessCharacter(@RequestBody Map<String, String> body) {
        String guessName = body.get("name");
        Long userId = Long.parseLong(body.get("userId")); // 🔑 importante
        Character hidden = dailyGuessService.getCharacterOfTheDay();

        Character guessed = characterService.getAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(guessName))
                .findFirst()
                .orElse(null);

        if (guessed == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Personaje no encontrado"));
        }

        boolean correct = guessed.getId().equals(hidden.getId());

        Map<String, Object> comparison = new HashMap<>();
        comparison.put("id", guessed.getId());
        comparison.put("imageFilename", guessed.getImageFilename());
        comparison.put("name", guessed.getName());

        comparison.put("gender", guessed.getGender());
        comparison.put("species", guessed.getSpecies());
        comparison.put("year", guessed.getYear());
        comparison.put("franchiseName", guessed.getFranchise().getName());

        comparison.put("genderMatch", guessed.getGender().equalsIgnoreCase(hidden.getGender()));
        comparison.put("speciesMatch", guessed.getSpecies().equalsIgnoreCase(hidden.getSpecies()));
        comparison.put("yearMatch", guessed.getYear().equalsIgnoreCase(hidden.getYear()));
        comparison.put("franchiseMatch", guessed.getFranchise().getName().equalsIgnoreCase(hidden.getFranchise().getName()));

        comparison.put("correct", correct);

        // ✅ Si ha acertado → otorgar puntos
        if (correct) {
            int puntos = calcularPuntosDaily(userId); // si tienes lógica de intentos o tiempo
            userScoreService.addDailyPoints(userId, puntos);
        }

        return ResponseEntity.ok(comparison);
    }

    private int calcularPuntosDaily(Long userId) {
        // Lógica provisional: 100 puntos por acierto
        return 100;

        // Puedes mejorarlo más adelante con lógica de intentos o tiempo
    }
}
