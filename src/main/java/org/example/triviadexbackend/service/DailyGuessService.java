package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.Character;
import org.example.triviadexbackend.entity.DailyGuess;
import org.example.triviadexbackend.repository.CharacterRepository;
import org.example.triviadexbackend.repository.DailyGuessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DailyGuessService {

    private final DailyGuessRepository dailyGuessRepository;
    private final CharacterRepository characterRepository;

    public DailyGuessService(DailyGuessRepository dailyGuessRepository, CharacterRepository characterRepository) {
        this.dailyGuessRepository = dailyGuessRepository;
        this.characterRepository = characterRepository;
    }

    public Character getCharacterOfTheDay() {
        LocalDate today = LocalDate.now();
        Optional<DailyGuess> optionalGuess = dailyGuessRepository.findByDate(today);

        if (optionalGuess.isPresent()) {
            return optionalGuess.get().getCharacter();
        }

        List<Character> allCharacters = characterRepository.findAll();
        if (allCharacters.isEmpty()) {
            throw new IllegalStateException("No hay personajes disponibles en la base de datos.");
        }

        Character randomCharacter = allCharacters.get(new Random().nextInt(allCharacters.size()));

        DailyGuess newGuess = new DailyGuess();
        newGuess.setDate(today);
        newGuess.setCharacter(randomCharacter);
        dailyGuessRepository.save(newGuess);

        return randomCharacter;
    }
}
