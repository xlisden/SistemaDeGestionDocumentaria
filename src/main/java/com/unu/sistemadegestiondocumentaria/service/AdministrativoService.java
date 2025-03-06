package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class AdministrativoService extends Repository<Administrativo> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private Validation validaciones = new Validation();

    public AdministrativoService(Class<Administrativo> type) {
        super(type);
    }

    @Override
    public void add(Administrativo t) {
        try {
            validaciones.validatePersona(t.getPersona());
            personaService.add(t.getPersona());
            int idPersona = personaService.getLastId();
            t.getPersona().setId(idPersona);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Administrativo ad = getById(id);
            if (ad == null) {
                throw new ValidationException(showWarning("El Administrativo no puede estar vacío."));
            }            
            validaciones.validatePersona(p);
            int idPersona = ad.getPersona().getId();
            personaService.update(idPersona, p);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public List<Administrativo> getAll() {
        return super.getAll();
    }

    @Override
    public Administrativo getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

}
