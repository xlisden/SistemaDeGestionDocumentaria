package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.IEgresadoRepository;
import com.unu.sistemadegestiondocumentaria.repository.IPersonaRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class EgresadoRepository implements IEgresadoRepository {

    private EntityManager em;
    private HibernateConfig hc = new HibernateConfig();
    private IPersonaRepository personaRepository = new PersonaRepository();
    private Validation validaciones = new Validation();

    @Override
    public void addEgresado(Egresado egresado) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateEgresado(egresado);
            em.persist(egresado);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateEgresado(int id, Egresado egresado) {
        em = hc.getEntityManager();
        Egresado e = getByIdEgresado(id);
        em.getTransaction().begin();
        try {
            validaciones.validateEgresado(egresado);
            e.setPersona(getPersona(e.getId(), egresado.getPersona()));
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteEgresado(int id) {
        em = hc.getEntityManager();
        Egresado e = getByIdEgresado(id);
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Egresado> getAllEgresados() {
        em = hc.getEntityManager();
        List<Egresado> egresados = em.createQuery("SELECT e FROM Egresado e", Egresado.class).getResultList();
        hc.closeConnection();
        return egresados;
    }

    @Override
    public Egresado getByIdEgresado(int id) {
        em = hc.getEntityManager();
        Egresado e = em.find(Egresado.class, id);
        hc.closeConnection();
        try {
            if (e == null) {
                throw new ValidationException(Validation.warningColor + "El Egresado no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    private Persona getPersona(int id, Persona p) {
        Persona persona = personaRepository.getByIdPersona(id);
        persona.setNombre(p.getNombre());
        persona.setApellidoPaterno(p.getApellidoPaterno());
        persona.setApellidoMaterno(p.getApellidoMaterno());
        persona.setGradoInstruccion(p.getGradoInstruccion());
        return persona;
    }

}
