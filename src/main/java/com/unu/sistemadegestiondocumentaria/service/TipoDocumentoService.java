package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class TipoDocumentoService extends Repository<TipoDocumento> {

    private Validation validaciones = new Validation();

    public TipoDocumentoService(Class<TipoDocumento> type) {
        super(type);
    }

    @Override
    public void add(TipoDocumento t) {
        try {
            validaciones.validateTipoDocumento(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, TipoDocumento t) {
        try {
            validaciones.validateTipoDocumento(t);
            TipoDocumento td = getById(id);
            if (td == null) {
                throw new ValidationException(showWarning("El Tipo de Documento " + id + " no existe en la base de datos."));
            }
            td.setNombre(t.getNombre());
            super.update(id, td);
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

    public List<TipoDocumento> getAll() {
        return super.getAll();
    }

    @Override
    public TipoDocumento getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

}
