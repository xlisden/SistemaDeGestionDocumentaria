package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class PersonaService extends Repository<Persona> {

    private final GradoInstruccionService giService = GradoInstruccionService.instanciar();
    
    private static PersonaService INSTANCIA;

    private PersonaService(Class<Persona> type) {
        super(type);
    }

    public static PersonaService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new PersonaService(Persona.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(Persona t) {
        GradoInstruccion gi = null;
        try {
            gi = giService.getById(t.getIdGradoInst());
//            if(gi == null){
//                throw new ValidationException(showWarning("El Persona de Instrucción de la Persona no puede estar vacío."));
//            }
            t.setGradoInstruccion(gi);
            Validation.validatePersona(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Persona t) {
        Persona p = null;
        try {
            p = getById(id);
            if (p == null) {
                throw new ValidationException(Validation.showWarning("La persona " + id + " no existe en la base de datos."));
            }

            GradoInstruccion gi = giService.getById(t.getIdGradoInst());
            t.setGradoInstruccion(gi);
            Validation.validatePersona(t);

            p.setNombre(t.getNombre());
            p.setApellidoPaterno(t.getApellidoPaterno());
            p.setApellidoMaterno(t.getApellidoMaterno());
            p.setGradoInstruccion(t.getGradoInstruccion());

            super.update(id, p);
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

    public List<Persona> getAllPersonas() {
        return super.getAll();
    }

    @Override
    public Persona getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

}
