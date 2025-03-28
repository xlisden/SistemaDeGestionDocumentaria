package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpedienteService extends Repository<Expediente> {

    private final EgresadoService egresadoService = EgresadoService.instanciar();

    private static ExpedienteService INSTANCIA;

    private ExpedienteService(Class<Expediente> type) {
        super(type);
    }

    public static ExpedienteService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new ExpedienteService(Expediente.class);
        }
        return INSTANCIA;
    }

    public void add(Persona t) {
        try {
            egresadoService.add(t);

            Egresado eg = egresadoService.getLast();
            if (!getAll().isEmpty() && eg.getId() == getLast().getEgresado().getId()) {
                return;
            }

            Expediente ex = new Expediente(eg);
            ex.setNroExpediente(getNroExp());
            super.add(ex);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Expediente exp = getById(id);
            if (exp == null) {
                // throw new ValidationException(Validation.showWarning("El Expediente no puede
                // estar vacío."));
                return;
            }

            egresadoService.update(exp.getEgresado().getId(), p);
            // no hacemos update del nroExp porque se supone que va de acuerdo al egresado.
            // Se supone que es unico, al igual que el egresado.
            // si eliminamos al egresado 3, esta bien que el sgte exp sea el 4, asi ya no
            // haya un exp 3
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
    public Expediente getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }

//    public List<Persona> getAll(boolean ordenAlfabetico) {
//        List<Expediente> lista = super.getAll();
//        List<Persona> personas = new ArrayList<>();
//        try {
//            return personas;
//        } catch (ValidationException e) {
//            e.printConsoleMessage();
//        }
//        return null;
//    }

    private int getNroExp() {
        return (getAll().isEmpty()) ? 1 : getLastId() + 1;
    }

}
