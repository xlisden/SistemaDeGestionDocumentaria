package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.*;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;

public class AdministrativoService extends Repository<Administrativo> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private Validation validaciones = new Validation();

    public AdministrativoService(Class<Administrativo> type) {
        super(type);
    }

    @Override
    public void add(Administrativo t) {
        try {
            validaciones.validateAdministrativo(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Administrativo t) {
        try {
            validaciones.validateAdministrativo(t);
            Administrativo ad = getById(id);
            if (ad == null) {
                throw new ValidationException(showWarning("El Administrativo no puede estar vacío."));
            }
            ad.setPersona(getPersona(ad.getId(), t.getPersona()));
            super.update(id, ad);
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

    public List<Administrativo> getAll() {
        String query = "SELECT ad FROM Administrativo ad";
        return super.getAll(query);
    }

    @Override
    public Administrativo getById(int id) {
        return super.getById(id);
    }

    private Persona getPersona(int id, Persona p) {
        Persona persona = personaService.getById(id);
        persona.setNombre(p.getNombre());
        persona.setApellidoPaterno(p.getApellidoPaterno());
        persona.setApellidoMaterno(p.getApellidoMaterno());
        persona.setGradoInstruccion(p.getGradoInstruccion());
        return persona;
    }

}
