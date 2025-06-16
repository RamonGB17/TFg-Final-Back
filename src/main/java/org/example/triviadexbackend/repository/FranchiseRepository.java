package org.example.triviadexbackend.repository;

import org.example.triviadexbackend.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    List<Franchise> findByType(String type); // "anime" o "videojuego"
}
