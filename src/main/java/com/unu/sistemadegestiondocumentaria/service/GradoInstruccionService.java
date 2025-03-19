package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class GradoInstruccionService extends Repository<GradoInstruccion> {

    private static GradoInstruccionService INSTANCIA;

    private GradoInstruccionService(Class<GradoInstruccion> type) {
        super(type);
    }
    
    public static GradoInstruccionService instanciar(){
        if (INSTANCIA == null) {
            INSTANCIA = new GradoInstruccionService(GradoInstruccion.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(GradoInstruccion t) {
        try {
            Validation.validateGradoInstruccion(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public void update(int id, GradoInstruccion t) {
        try {
            Validation.validateGradoInstruccion(t);
            
            GradoInstruccion gi = getById(id);
            if (gi == null) {
                return;
            }

            gi.setNombre(t.getNombre());
            
            super.update(id, gi);
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
    public GradoInstruccion getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }

}
