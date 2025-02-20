package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.IPersonaRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class PersonaRepository implements IPersonaRepository {

    private HibernateConfig hc = new HibernateConfig();
    private Validation validaciones = new Validation();
    private EntityManager em;

    @Override
    public void addPersona(Persona persona) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validatePersona(persona);
            em.persist(persona);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updatePersona(int id, Persona persona) {
        em = hc.getEntityManager();
        Persona p = getByIdPersona(id);
        em.getTransaction().begin();
        try {
            validaciones.validatePersona(persona);
            p.setNombre(persona.getNombre());
            p.setApellidoPaterno(persona.getApellidoPaterno());
            p.setApellidoMaterno(persona.getApellidoMaterno());
            p.setGradoInstruccion(persona.getGradoInstruccion());
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deletePersona(int id) {
        Persona p = getByIdPersona(id);
        em = hc.getEntityManager();
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Persona> getAllPersonas() {
        em = hc.getEntityManager();
        List<Persona> estados = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        hc.closeConnection();
        return estados;
    }

    @Override
    public Persona getByIdPersona(int id) {
        em = hc.getEntityManager();
        Persona p = em.find(Persona.class, id);
        hc.closeConnection();
        try {
            if (p == null) {
                throw new ValidationException(Validation.warningColor + "La Persona no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

}
