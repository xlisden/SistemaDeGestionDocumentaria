package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetDestinatarioService extends Repository<DetalleDestinatario> {

//    private final DocumentoService documentoService = DocumentoService.instanciar();
    private final AdministrativoService administrativoService = AdministrativoService.instanciar();
    
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

    public void update(DetalleDestinatario t, Administrativo nuevoDest) {
        int id = 0;
        DetalleDestinatario detDest = null;
        try {
            id = getId(t.getDocumento().getId(), t.getDestinatario().getId());
            detDest = getById(id);
            if (detDest == null) {
                throw new ValidationException(Validation.showWarning("El Det. Destinatario no puede estar vacío."));
            }

            Validation.validateDetDestinatario(t);
            detDest.setDestinatario(nuevoDest);

            super.update(id, detDest);
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

    public int getId(int idDoc, int idDest) {
        int id = 0;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idDoc", idDoc);
        parametros.put("idDest", idDest);
        try {
            DetalleDestinatario detDest = getByQuery("SELECT x FROM DetalleDestinatario x WHERE x.documento.id = :idDoc AND x.destinatario.id = :idDest", parametros);
            if (!(detDest == null)) {
                id = detDest.getId();
                return id;
            }
        } catch (ValidationException e) {
            e.printMessage();
        }
        return 0;
    }

}
