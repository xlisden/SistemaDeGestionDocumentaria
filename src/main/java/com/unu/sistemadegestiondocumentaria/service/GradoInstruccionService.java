package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class GradoInstruccionService extends Repository<GradoInstruccion> {

    

    public GradoInstruccionService(Class<GradoInstruccion> type) {
        super(type);
    }

    @Override
    public void add(GradoInstruccion t) {
        try {
            Validation.validateGradoInstruccion(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, GradoInstruccion t) {
        try {
            Validation.validateGradoInstruccion(t);
            GradoInstruccion gi = getById(id);
            // if (gi == null) {
            //     throw new ValidationException(Validation.showWarning("El Grado de Instrucción "+ id + " no existe en la base de datos."));
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
        return super.getAll();
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
