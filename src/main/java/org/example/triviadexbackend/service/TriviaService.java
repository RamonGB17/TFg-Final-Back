package org.example.triviadexbackend.service;

import jakarta.transaction.Transactional;
import org.example.triviadexbackend.dto.TriviaDTO;
import org.example.triviadexbackend.entity.Trivia;
import org.example.triviadexbackend.entity.TriviaOption;
import org.example.triviadexbackend.entity.TriviaType;
import org.example.triviadexbackend.repository.TriviaOptionRepository;
import org.example.triviadexbackend.repository.TriviaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TriviaService {

    private final TriviaRepository triviaRepository;
    private final TriviaOptionRepository triviaOptionRepository;

    public TriviaService(TriviaRepository triviaRepository, TriviaOptionRepository triviaOptionRepository) {
        this.triviaRepository = triviaRepository;
        this.triviaOptionRepository = triviaOptionRepository;
    }

    public List<TriviaDTO> getRandomTriviaByCategory(String category) {
        // Convertimos el String category en TriviaType
        TriviaType triviaType = TriviaType.valueOf(category.toUpperCase());

        // Obtenemos la lista de preguntas por tipo, ya en orden aleatorio desde el Repository
        List<Trivia> triviaList = triviaRepository.findByType(triviaType);

        List<TriviaDTO> result = new ArrayList<>();

        // Solo enviamos 10 preguntas como máximo
        triviaList.stream().limit(10).forEach(trivia -> {
            List<TriviaOption> options = triviaOptionRepository.findByTrivia(trivia);
            List<String> optionTexts = options.stream().map(TriviaOption::getOptionText).toList();

            TriviaDTO dto = new TriviaDTO();
            dto.setQuestion(trivia.getQuestion());
            dto.setCorrectAnswer(trivia.getCorrectAnswer());
            dto.setType(trivia.getType().name());
            dto.setOptions(optionTexts);

            result.add(dto);
        });

        return result;
    }

    @Transactional
    public void saveTrivia(TriviaDTO triviaDTO) {
        // Guardar la pregunta
        Trivia trivia = new Trivia();
        trivia.setQuestion(triviaDTO.getQuestion());
        trivia.setCorrectAnswer(triviaDTO.getCorrectAnswer());
        trivia.setType(TriviaType.valueOf(triviaDTO.getType().toUpperCase()));

        Trivia savedTrivia = triviaRepository.save(trivia);

        // Guardar las opciones
        for (String optionText : triviaDTO.getOptions()) {
            TriviaOption option = new TriviaOption();
            option.setTrivia(savedTrivia);
            option.setOptionText(optionText);
            triviaOptionRepository.save(option);
        }
    }
}
