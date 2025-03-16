package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class EstadoService extends Repository<Estado> {

    private static EstadoService INSTANCIA;

    private EstadoService(Class<Estado> type) {
        super(type);
    }

    public static EstadoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new EstadoService(Estado.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(Estado t) {
        try {
            Validation.validateEstado(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Estado t) {
        try {
            Validation.validateEstado(t);

            Estado est = getById(id);
            if (est == null) {
                return;
            }
            est.setNombre(t.getNombre());

            super.update(id, est);
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
    public Estado getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

    public Estado getByNombre(String nombre) {
        Estado est = null;
        for (Estado e : getAll()) {
            if (e.getNombre().equals(nombre)) {
                est = e;
            }
        }
        return est;
    }

}
