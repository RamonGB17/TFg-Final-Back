package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.entity.Franchise;
import org.example.triviadexbackend.service.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@CrossOrigin
public class FranchiseController {

    private final FranchiseService service;

    public FranchiseController(FranchiseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Franchise> getAll() {
        return service.getAll();
    }

    @GetMapping("/type/{type}")
    public List<Franchise> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Franchise create(@RequestBody Franchise franchise) {
        return service.create(franchise);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
