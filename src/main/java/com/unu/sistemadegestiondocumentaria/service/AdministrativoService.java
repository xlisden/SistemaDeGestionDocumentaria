package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class AdministrativoService extends Repository<Administrativo> {

    private final PersonaService personaService = PersonaService.instanciar();

    private static AdministrativoService INSTANCIA;

    private AdministrativoService(Class<Administrativo> type) {
        super(type);
    }

    public static AdministrativoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new AdministrativoService(Administrativo.class);
        }
        return INSTANCIA;
    }

    public void add(Persona t) {
        try {
            personaService.add(t);

            int idPersona = personaService.getLastId();
            if (!getAll().isEmpty() && idPersona == getLast().getPersona().getId()) {
                return;
            }
            t.setId(idPersona);

            Administrativo ad = new Administrativo(t);
            super.add(ad);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Administrativo ad = getById(id);
            if (ad == null) {
                // throw new ValidationException(Validation.showWarning("El Administrativo no puede estar vacío."));
                return;
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
    public Administrativo getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

}
