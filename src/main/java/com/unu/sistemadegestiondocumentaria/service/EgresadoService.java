package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class EgresadoService extends Repository<Egresado> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private Validation validaciones = new Validation();
    private ExpedienteService expedienteService = new ExpedienteService(Expediente.class);

    public EgresadoService(Class<Egresado> type) {
        super(type);
        System.out.println(Validation.infoColor + "constructor EgresadoService" + Validation.normalColor);
    }

    @Override
    public void add(Egresado t) {
        try {
            validaciones.validatePersona(t.getPersona());
            personaService.add(t.getPersona());
            int idPersona = personaService.getLastId();
            t.getPersona().setId(idPersona);
            super.add(t);            
            // expedienteService.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            expedienteService.update(id, p);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void delete(int id) {
        try {
            Egresado eg = getById(id);
            super.delete(id);
            personaService.delete(eg.getPersona().getId());
            expedienteService.delete(expedienteService.getByEgresado(id).getId());
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public List<Egresado> getAll() {
        System.out.println(Validation.infoColor + "get all in egresado service" + Validation.normalColor);
        return super.getAll();
    }

    @Override
    public Egresado getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

}
