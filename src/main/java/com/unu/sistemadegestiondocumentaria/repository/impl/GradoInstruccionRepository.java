package com.unu.sistemadegestiondocumentaria.repository.impl;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.IGradoInstruccionRepository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import java.util.List;
import javax.persistence.EntityManager;

public class GradoInstruccionRepository implements IGradoInstruccionRepository {

    private HibernateConfig hc = new HibernateConfig();
    private Validation validaciones = new Validation();
    private EntityManager em;

    @Override
    public void addGradoInstruccion(GradoInstruccion gradoInstruccion) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateGradoInstruccion(gradoInstruccion);
            em.persist(gradoInstruccion);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateGradoInstruccion(int id, GradoInstruccion gradoInstruccion) {
        em = hc.getEntityManager();
        GradoInstruccion gi = getByIdGradoInstruccion(id);
        em.getTransaction().begin();
        try {
            validaciones.validateGradoInstruccion(gradoInstruccion);
            gi.setNombre(gradoInstruccion.getNombre());
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteGradoInstruccion(int id) {
        em = hc.getEntityManager();
        GradoInstruccion gi = getByIdGradoInstruccion(id);
        em.getTransaction().begin();
        em.remove(gi);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<GradoInstruccion> getAllGradoInstrucciones() {
        em = hc.getEntityManager();
        List<GradoInstruccion> gradosInstruccion = em.createQuery("SELECT gi FROM GradoInstruccion gi", GradoInstruccion.class).getResultList();
        hc.closeConnection();
        return gradosInstruccion;
    }

    @Override
    public GradoInstruccion getByIdGradoInstruccion(int id) {
        em = hc.getEntityManager();
        GradoInstruccion gi = em.find(GradoInstruccion.class, id);
        hc.closeConnection();
        try {
            if (gi == null) {
                throw new ValidationException(Validation.warningColor + "El Grado de Instrucción no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return gi;
    }

}
