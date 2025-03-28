package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.Collections;
import java.util.List;

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
            e.printConsoleMessage();
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
    public Administrativo getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }

    public List<Administrativo> getAllAdminiOrdenAlfNombre() {
        List<Administrativo> lista = super.getAll();
        if (lista != null) {
            Collections.sort(lista, (x, y) -> x.getPersona().getNombre().compareToIgnoreCase(y.getPersona().getNombre()));
        }
        return lista;
    }
    
    public List<Administrativo> getAllAdminiOrdenAlfApPaterno() {
        List<Administrativo> lista = super.getAll();
        if (lista != null) {
            Collections.sort(lista, (x, y) -> x.getPersona().getApellidoPaterno().compareToIgnoreCase(y.getPersona().getApellidoPaterno()));
        }
        return lista;
    }

}
