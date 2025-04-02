package com.ormexample.demo.service;

import com.ormexample.demo.dto.PersonaCreateDTO;
import com.ormexample.demo.model.Persona;
import com.ormexample.demo.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import com.ormexample.demo.exception.PersonaExceptions;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona savePersona(PersonaCreateDTO personaCreateDTO) {
        Persona user = new Persona();
        user.setNombre(personaCreateDTO.getNombre());
        user.setEdad(personaCreateDTO.getEdad());

        personaRepository.save(user);

        return user;
    }

    public Persona getPersonaById(Long id) {
        Persona found = personaRepository.findById(id)
                .orElseThrow(() -> new PersonaExceptions.PersonaNotFoundException(id));
        return found;
    }

    public List<Persona> getAllPersonas() {
        List<Persona> users = personaRepository.findAll();
        return users;
    }

    public Persona updatePersona(Long id, PersonaCreateDTO userUpdateDTO) {
        if (!personaRepository.existsById(id)) {
            throw new PersonaExceptions.PersonaNotFoundException(id);
        }

        Persona user = new Persona();
        user.setId(id);
        user.setNombre(userUpdateDTO.getNombre());
        user.setEdad(userUpdateDTO.getEdad());

        return personaRepository.update(user);
    }

    public void deletePersona(Long id) {
        personaRepository.delete(id);
    }
}
