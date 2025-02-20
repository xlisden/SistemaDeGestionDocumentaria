package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.IAdministrativoRepository;
import com.unu.sistemadegestiondocumentaria.repository.IPersonaRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class AdministrativoRepository implements IAdministrativoRepository {

    private EntityManager em;
    private HibernateConfig hc = new HibernateConfig();
    private IPersonaRepository personaRepository = new PersonaRepository();
    private Validation validaciones = new Validation();

    @Override
    public void addAdministrativo(Administrativo administrativo) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateAdministrativo(administrativo);
            em.persist(administrativo);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateAdministrativo(int id, Administrativo administrativo) {
        em = hc.getEntityManager();
        Administrativo ad = getByIdAdministrativo(id);
        em.getTransaction().begin();
        try {
            validaciones.validateAdministrativo(administrativo);
            ad.setPersona(getPersona(ad.getId(), administrativo.getPersona()));
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteAdministrativo(int id) {
        em = hc.getEntityManager();
        Administrativo ad = getByIdAdministrativo(id);
        em.getTransaction().begin();
        em.remove(ad);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Administrativo> getAllAdministrativos() {
        em = hc.getEntityManager();
        List<Administrativo> administrativos = em.createQuery("SELECT ad FROM Administrativo ad", Administrativo.class).getResultList();
        hc.closeConnection();
        return administrativos;
    }

    @Override
    public Administrativo getByIdAdministrativo(int id) {
        em = hc.getEntityManager();
        Administrativo ad = em.find(Administrativo.class, id);
        hc.closeConnection();
        try {
            if (ad == null) {
                throw new ValidationException(Validation.warningColor + "El Administrativo no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return ad;
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
