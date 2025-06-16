package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.User;
import org.example.triviadexbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(Long id, User newData) {
        return repository.findById(id).map(existing -> {
            existing.setUsername(newData.getUsername());
            existing.setEmail(newData.getEmail());
            existing.setPasswordHash(newData.getPasswordHash());
            existing.setAvatarUrl(newData.getAvatarUrl());
            existing.setTheme(newData.getTheme());
            return repository.save(existing);
        }).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
