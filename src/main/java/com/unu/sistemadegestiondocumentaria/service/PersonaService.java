package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.*;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;

public class PersonaService extends Repository<Persona> {

    private Validation validaciones = new Validation();

    public PersonaService(Class<Persona> type) {
        super(type);
    }

    @Override
    public void add(Persona t) {
        try {
            validaciones.validatePersona(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Persona t) {
        try {
            validaciones.validatePersona(t);
            Persona p = getById(id);
            if (p == null) {
                throw new ValidationException(showWarning("La persona no puede estar vacía."));
            }
            p.setNombre(t.getNombre());
            p.setApellidoPaterno(t.getApellidoPaterno());
            p.setApellidoMaterno(t.getApellidoMaterno());
            super.update(id, p);
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

    public List<Persona> getAllPersonas() {
        String query = "SELECT p FROM Persona p";
        return super.getAll(query);        
    }

    @Override
    public Persona getById(int id) {
        return super.getById(id);
    }


}
