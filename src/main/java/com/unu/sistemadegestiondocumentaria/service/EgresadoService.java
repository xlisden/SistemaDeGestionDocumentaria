package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

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
        try {
            personaService.add(t);
            int idPersona = personaService.getLastId();
            
            if (!getAll().isEmpty() && idPersona == getLast().getPersona().getId() || t.getIdGradoInst() != 1) {
                return;
            }
            t.setId(idPersona);

            Egresado eg = new Egresado(t);
            super.add(eg);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Egresado eg = getById(id);
            if (eg == null) {
                // throw new ValidationException(Validation.showWarning("El Egresado no puede estar vacío."));
                return;
            }

            int idPersona = eg.getPersona().getId();

            personaService.update(idPersona, p);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public Egresado getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }

}
