package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class EgresadoService extends Repository<Egresado> {

    private final PersonaService personaService = PersonaService.instanciar();

    private static EgresadoService INSTANCIA;

    private EgresadoService(Class<Egresado> type) {
        super(type);
    }

    public static EgresadoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new EgresadoService(Egresado.class);
        }
        return INSTANCIA;
    }

    public void add(Persona t) {
        int idPersona = 0;
        Egresado eg = null;
        try {
            personaService.add(t);
            idPersona = personaService.getLastId();
            if (!getAll().isEmpty() && idPersona == getLast().getPersona().getId()) {
                return;
            }
            t.setId(idPersona);

            eg = new Egresado(t);
            super.add(eg);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Egresado eg = getById(id);
            if (eg == null) {
                throw new ValidationException(Validation.showWarning("El Egresado no puede estar vacío."));
            }

            int idPersona = eg.getPersona().getId();
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
    public List<Egresado> getAll() {
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
