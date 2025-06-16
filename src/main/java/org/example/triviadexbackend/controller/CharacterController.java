package org.example.triviadexbackend.controller;

import org.example.triviadexbackend.dto.CharacterDTO;
import org.example.triviadexbackend.entity.Character;
import org.example.triviadexbackend.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    // GET → Listar personajes → PÚBLICO
    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAll();
        return ResponseEntity.ok(characters);
    }

    // GET → Obtener personaje por ID → PÚBLICO (para detalle-ficha o para admin editar)
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        return characterService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST → Crear personaje → ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveCharacter(@RequestBody CharacterDTO characterDTO) {
        characterService.saveCharacter(characterDTO);
        return ResponseEntity.ok().build();
    }

    // PUT → Actualizar personaje → ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {
        characterService.updateCharacter(id, characterDTO);
        return ResponseEntity.ok().build();
    }

    // DELETE → Eliminar personaje → ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
