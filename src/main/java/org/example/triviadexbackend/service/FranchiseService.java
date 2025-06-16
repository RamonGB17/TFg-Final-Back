package org.example.triviadexbackend.service;

import org.example.triviadexbackend.entity.Franchise;
import org.example.triviadexbackend.repository.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {

    private final FranchiseRepository repository;

    public FranchiseService(FranchiseRepository repository) {
        this.repository = repository;
    }

    public List<Franchise> getAll() {
        return repository.findAll();
    }

    public List<Franchise> getByType(String type) {
        return repository.findByType(type);
    }

    public Optional<Franchise> getById(Long id) {
        return repository.findById(id);
    }

    public Franchise create(Franchise franchise) {
        return repository.save(franchise);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
