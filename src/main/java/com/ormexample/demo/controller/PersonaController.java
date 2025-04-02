package com.ormexample.demo.controller;

import com.ormexample.demo.dto.PersonaCreateDTO;
import com.ormexample.demo.model.Persona;
import com.ormexample.demo.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> createUser(@Valid @RequestBody PersonaCreateDTO user) {
        Persona createdUser = personaService.savePersona(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getUserById(@PathVariable Long id) {
        Persona user = personaService.getPersonaById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllUsers() {
        List<Persona> users = personaService.getAllPersonas();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updateUser(@PathVariable Long id, @Valid @RequestBody PersonaCreateDTO user) {
        Persona updated = personaService.updatePersona(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.ok("Persona deleted successfully!");
    }
}
