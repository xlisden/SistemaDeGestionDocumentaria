package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.IExpedienteRepository;
import com.unu.sistemadegestiondocumentaria.repository.IPersonaRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class ExpedienteRepository implements IExpedienteRepository {

    private EntityManager em;
    private HibernateConfig hc = new HibernateConfig();
    private IPersonaRepository personaRepository = new PersonaRepository();
    private Validation validaciones = new Validation();

    @Override
    public void addExpediente(Expediente expediente) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateExpediente(expediente);
            em.persist(expediente);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateExpediente(int id, Expediente expediente) {
        em = hc.getEntityManager();
        Expediente e = getByIdExpediente(id);
        em.getTransaction().begin();
        try {
            validaciones.validateExpediente(expediente);
            e.setEgresado(expediente.getEgresado());
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteExpediente(int id) {
        em = hc.getEntityManager();
        Expediente e = getByIdExpediente(id);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Expediente> getAllExpedientes() {
        em = hc.getEntityManager();
        List<Expediente> expedientes = em.createQuery("SELECT e FROM Expediente e", Expediente.class).getResultList();
        hc.closeConnection();
        return expedientes;
    }

    @Override
    public Expediente getByIdExpediente(int id) {
        em = hc.getEntityManager();
        Expediente e = em.find(Expediente.class, id);
        hc.closeConnection();
        try {
            if (e == null) {
                throw new ValidationException(Validation.warningColor + "El Expediente no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

}
