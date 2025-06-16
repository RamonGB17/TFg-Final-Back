package org.example.triviadexbackend.service;

import jakarta.transaction.Transactional;
import org.example.triviadexbackend.dto.CharacterDTO;
import org.example.triviadexbackend.entity.Character;
import org.example.triviadexbackend.entity.Franchise;
import org.example.triviadexbackend.repository.CharacterRepository;
import org.example.triviadexbackend.repository.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository repository;
    private final FranchiseRepository franchiseRepository;

    public CharacterService(CharacterRepository repository, FranchiseRepository franchiseRepository) {
        this.repository = repository;
        this.franchiseRepository = franchiseRepository;
    }

    public List<Character> getAll() {
        return repository.findAll();
    }

    public List<Character> getByFranchiseId(Long franchiseId) {
        return repository.findByFranchiseId(franchiseId);
    }

    public Optional<Character> getById(Long id) {
        return repository.findById(id);
    }

    public Character create(Character character) {
        return repository.save(character);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // 👉 Nuevo método para el admin (crear desde CharacterDTO)
    @Transactional
    public void saveCharacter(CharacterDTO characterDTO) {
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setGender(characterDTO.getGender());
        character.setSpecies(characterDTO.getSpecies());
        character.setYear(characterDTO.getYear());
        character.setImageFilename(characterDTO.getImageFilename());

        Franchise franchise = franchiseRepository.findById(characterDTO.getFranchiseId())
                .orElseThrow(() -> new RuntimeException("Franchise no encontrada con id " + characterDTO.getFranchiseId()));

        character.setFranchise(franchise);

        repository.save(character);
    }

    // 👉 Nuevo método para el admin (actualizar desde CharacterDTO)
    @Transactional
    public void updateCharacter(Long id, CharacterDTO characterDTO) {
        Character character = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character no encontrado con id " + id));

        character.setName(characterDTO.getName());
        character.setGender(characterDTO.getGender());
        character.setSpecies(characterDTO.getSpecies());
        character.setYear(characterDTO.getYear());
        character.setImageFilename(characterDTO.getImageFilename());

        Franchise franchise = franchiseRepository.findById(characterDTO.getFranchiseId())
                .orElseThrow(() -> new RuntimeException("Franchise no encontrada con id " + characterDTO.getFranchiseId()));

        character.setFranchise(franchise);

        repository.save(character);
    }
}
