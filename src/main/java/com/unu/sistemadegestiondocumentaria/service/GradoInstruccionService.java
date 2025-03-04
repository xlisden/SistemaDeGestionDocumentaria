package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class GradoInstruccionService extends Repository<GradoInstruccion> {

    private Validation validaciones = new Validation();

    public GradoInstruccionService(Class<GradoInstruccion> type) {
        super(type);
    }

    @Override
    public void add(GradoInstruccion t) {
        try {
            validaciones.validateGradoInstruccion(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, GradoInstruccion t) {
        try {
            validaciones.validateGradoInstruccion(t);
            GradoInstruccion gi = getById(id);
            // if (gi == null) {
            //     throw new ValidationException(showWarning("El Grado de Instrucción "+ id + " no existe en la base de datos."));
            // }
            gi.setNombre(t.getNombre());
            super.update(id, gi);
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

    public List<GradoInstruccion> getAll() {
        String query = "SELECT gi FROM GradoInstruccion gi";
        return super.getAll(query);
    }

    @Override
    public GradoInstruccion getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

}
