package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.*;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;

public class EstadoService extends Repository<Estado> {

    private Validation validaciones = new Validation();

    public EstadoService(Class<Estado> type) {
        super(type);
    }

    @Override
    public void add(Estado t) {
        try {
            validaciones.validateEstado(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Estado t) {
        try {
            validaciones.validateEstado(t);
            Estado est = getById(id);
            if (est == null) {
                throw new ValidationException(showWarning("El Estado no puede estar vacío."));
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

    public List<Estado> getAll() {
        String query = "SELECT est FROM Estado est";
        return super.getAll(query);
    }

    @Override
    public Estado getById(int id) {
        return super.getById(id);
    }
    
    public Estado getByNombre(String nombre){
        Estado est = null;
        for(Estado e: getAll()){
            if(e.getNombre().equals(nombre)){
                est = e;
            }
        }
        return est;
    }

}
