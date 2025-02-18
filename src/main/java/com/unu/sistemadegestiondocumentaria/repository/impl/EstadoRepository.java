package com.unu.sistemadegestiondocumentaria.repository.impl;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.IEstadoRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class EstadoRepository implements IEstadoRepository {

    private HibernateConfig hc = new HibernateConfig();
    private Validation validaciones = new Validation();
    private EntityManager em;

    @Override
    public void addEstado(Estado estado) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateEstado(estado);
            em.persist(estado);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateEstado(int id, Estado estado) {
        em = hc.getEntityManager();
        Estado e = getByIdEstado(id);
        em.getTransaction().begin();
        try {
            validaciones.validateEstado(estado);
            e.setNombre(estado.getNombre());
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteEstado(int id) {
        em = hc.getEntityManager();
        Estado e = getByIdEstado(id);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Estado> getAllEstados() {
        em = hc.getEntityManager();
        List<Estado> estados = em.createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
        hc.closeConnection();
        return estados;
    }

    @Override
    public Estado getByIdEstado(int id) {
        em = hc.getEntityManager();
        Estado e = em.find(Estado.class, id);
        hc.closeConnection();
        try {
            if (e == null) {
                throw new ValidationException(Validation.warningColor + "El Estado no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

}
