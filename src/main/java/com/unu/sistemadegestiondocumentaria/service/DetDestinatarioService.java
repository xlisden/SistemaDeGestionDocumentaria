package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.List;

public class DetDestinatarioService extends Repository<DetalleDestinatario> {
    
//    private final DocumentoService documentoService = DocumentoService.instanciar();
//    private final AdministrativoService administrativoService  = AdministrativoService.instanciar();

    private static DetDestinatarioService INSTANCIA;

    private DetDestinatarioService(Class<DetalleDestinatario> type) {
        super(type);
    }

    public static DetDestinatarioService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DetDestinatarioService(DetalleDestinatario.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(DetalleDestinatario t) {
        try {
            Validation.validateDetDestinatario(t);
            super.add(t);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(int id, DetalleDestinatario t) {
        try {
            DetalleDestinatario detDest = getById(id);
            if (detDest == null) {
                throw new ValidationException(Validation.showWarning("El Det. Destinatario no puede estar vacío."));
            }
            
            Validation.validateDetDestinatario(t);
            detDest.setDocumento(t.getDocumento());
            detDest.setDestinatario(t.getDestinatario());
            
            super.update(id, t);
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
    public List<DetalleDestinatario> getAll() {
        return super.getAll();
    }

    @Override
    public DetalleDestinatario getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

}
