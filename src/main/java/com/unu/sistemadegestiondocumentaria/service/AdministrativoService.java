package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class AdministrativoService extends Repository<Administrativo> {

    private PersonaService personaService = new PersonaService(Persona.class);
    
    public AdministrativoService(Class<Administrativo> type) {
        super(type);
    }

    public void add(Persona t) {
        int idPersona = 0;
        Administrativo ad = null;
        try {
            personaService.add(t);
            idPersona = personaService.getLastId();
            if(!getAll().isEmpty() && idPersona == getLast().getPersona().getId()) {
                return;
            }
            t.setId(idPersona);
            
            ad = new Administrativo(t);
            super.add(ad);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Administrativo ad = getById(id);
            if (ad == null) {
                throw new ValidationException(Validation.showWarning("El Administrativo no puede estar vacío."));
            }            

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
