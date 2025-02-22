package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.*;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;

public class EgresadoService extends Repository<Egresado> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private Validation validaciones = new Validation();

    public EgresadoService(Class<Egresado> type) {
        super(type);
    }

    @Override
    public void add(Egresado t) {
        try {
            validaciones.validateEgresado(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Egresado t) {
        try {
            validaciones.validateEgresado(t);
            Egresado eg = getById(id);
            if (eg == null) {
                throw new ValidationException(showWarning("El Egresado no puede estar vacío."));
            }
            eg.setPersona(getPersona(eg.getId(), t.getPersona()));
            super.update(id, eg);
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

    public List<Egresado> getAll() {
        String query = "SELECT e FROM Egresado e";
        return super.getAll(query);
    }

    @Override
    public Egresado getById(int id) {
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
