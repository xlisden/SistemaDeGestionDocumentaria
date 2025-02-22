package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.*;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import java.util.List;

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
            if (gi == null) {
                throw new ValidationException(showWarning("El Grado de Instrucción no puede estar vacío."));
            }
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
        return super.getById(id);
    }

}
