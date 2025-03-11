package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class OficioService extends Repository<Oficio> {

    private final DocumentoService docService = new DocumentoService(Documento.class);

    public OficioService(Class<Oficio> type) {
        super(type);
    }

    @Override
    public void add(Oficio t) {
        Documento doc = null;
        try {
            doc = t.getDocumento();
            docService.add(doc);

            Validation.validateOficio(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Oficio t) {
        Documento doc = null;
        Oficio oficio = null;
        try {
            oficio = getById(id);
            if (oficio == null) {
                throw new ValidationException(Validation.showWarning("El Oficio no puede estar vacío."));
            }
            
            doc = docService.getById(oficio.getDocumento().getId());
            doc = docService.setDocumento(doc, t.getDocumento());
            t.setDocumento(doc);

            Validation.validateOficio(t);
            docService.update(oficio.getDocumento().getId(), doc);
            oficio.setAsunto(t.getAsunto());
            oficio.setReferencia(t.getReferencia());

            super.update(id, oficio);
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
    public List<Oficio> getAll() {
        return super.getAll();
    }

    @Override
    public Oficio getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

    public void updateEstadoDocumento(int id) {
        int idDoc = 0;
        Oficio oficio = null;
        try {
            oficio = getById(id);
            idDoc = oficio.getDocumento().getId();

            docService.updateEstadoDocumento(idDoc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

}
