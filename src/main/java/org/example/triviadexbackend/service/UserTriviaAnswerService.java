package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.UserTriviaAnswer;
import org.example.triviadexbackend.repository.UserTriviaAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTriviaAnswerService {

    private final UserTriviaAnswerRepository repository;

    public UserTriviaAnswerService(UserTriviaAnswerRepository repository) {
        this.repository = repository;
    }

    public List<UserTriviaAnswer> getAll() {
        return repository.findAll();
    }

    public List<UserTriviaAnswer> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<UserTriviaAnswer> getById(Long id) {
        return repository.findById(id);
    }

    public UserTriviaAnswer create(UserTriviaAnswer answer) {
        return repository.save(answer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
