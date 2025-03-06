package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class ExpedienteService extends Repository<Expediente> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private EgresadoService egresadoService = new EgresadoService(Egresado.class);
    private Validation validaciones = new Validation();
    private HibernateConfig hc = new HibernateConfig();
    private EntityManager em;

    public ExpedienteService(Class<Expediente> type) {
        super(type);
    }

    public void add(Egresado t) {
        try {
            validaciones.validatePersona(t.getPersona());
            personaService.add(t.getPersona());
            int idPersona = personaService.getLastId();
            t.getPersona().setId(idPersona);
            Expediente ex = new Expediente(t);
            super.add(ex);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Expediente exp = getById(id);
            if (exp == null) {
                throw new ValidationException(showWarning("El Expediente no puede estar vacío."));
            }
            validaciones.validatePersona(p);
            int idPersona = exp.getEgresado().getPersona().getId();
            personaService.update(idPersona, p);
            super.update(id, exp);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    //tambien podriamos cambiar los datos del egresado del expediente, directamente desde el expediente
    // aunque al final llegariamos al update del expediente
    public void updateEgresado(int idEg, Egresado t) {
        egresadoService.update(idEg, t);
    }

    @Override
    public void delete(int id) {
        try {
            Expediente ex = getById(id);
            super.delete(id);
            egresadoService.delete(ex.getEgresado().getId());
            personaService.delete(ex.getEgresado().getPersona().getId());
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public List<Expediente> getAll() {
        return super.getAll();
    }

    @Override
    public Expediente getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

    public Expediente getByEgresado(int idEg){
        Expediente t = null;
        em = hc.getEntityManager();
        t = em.createQuery("SELECT x FROM Expediente x WHERE x.idEgresado = :idEg", Expediente.class).setMaxResults(1).getSingleResult();
        hc.closeConnection();
        return t;        
    }

}
