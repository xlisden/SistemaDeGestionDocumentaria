package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class TipoDocumentoService extends Repository<TipoDocumento> {

    private static TipoDocumentoService INSTANCIA;

    private TipoDocumentoService(Class<TipoDocumento> type) {
        super(type);
    }
    
    public static TipoDocumentoService instanciar(){
        if (INSTANCIA == null) {
            INSTANCIA = new TipoDocumentoService(TipoDocumento.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(TipoDocumento t) {
        try {
            Validation.validateTipoDocumento(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, TipoDocumento t) {
        try {
            Validation.validateTipoDocumento(t);
            
            TipoDocumento td = getById(id);
            if (td == null) {
                return;
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
