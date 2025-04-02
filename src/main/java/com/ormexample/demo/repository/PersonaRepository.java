package com.ormexample.demo.repository;

import com.ormexample.demo.exception.PersonaExceptions;
import com.ormexample.demo.model.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Persona persona) {
        entityManager.persist(persona);
    }

    public List<Persona> findAll() {
        return entityManager.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
    }

    public Optional<Persona> findById(Long id) {
        Persona persona = entityManager.find(Persona.class, id);
        return Optional.ofNullable(persona);
    }

    public Persona update(Persona persona) {
        return entityManager.merge(persona);
    }

    public void delete(Long id) {
        Persona persona = entityManager.find(Persona.class, id);
        if (persona != null) {
            entityManager.remove(persona);
        } else {
            throw new PersonaExceptions.PersonaNotFoundException(id);
        }
    }

    public Boolean existsById(Long id) {
        Persona persona = entityManager.find(Persona.class, id);
        return persona != null;
    }
}
