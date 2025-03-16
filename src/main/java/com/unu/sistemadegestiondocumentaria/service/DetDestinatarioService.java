package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

import java.util.HashMap;
import java.util.Map;


public class DetDestinatarioService extends Repository<DetalleDestinatario> {

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
        try {
            int id = getId(t.getDocumento().getId(), t.getDestinatario().getId());

            DetalleDestinatario detDest = getById(id);
            if (detDest == null) {
                // throw new ValidationException(Validation.showWarning("El Det. Destinatario no puede estar vacío."));
                return;
            }

            Validation.validateDetDestinatario(t);
            detDest.setDestinatario(nuevoDest);

            super.update(id, detDest);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void delete(int idDoc, int idDest) {        
        try {
            int id = getId(idDoc, idDest);
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
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
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idDoc", idDoc);
            parametros.put("idDest", idDest);

            DetalleDestinatario detDest = getByQuery( "SELECT x FROM DetalleDestinatario x WHERE x.documento.id = :idDoc AND x.destinatario.id = :idDest", parametros);
            if (detDest == null){
                return 0;
            }

            id = detDest.getId();
        } catch (ValidationException e) {
            e.printMessage();
        }
        return id;
    }

    public void deleteByDoc(int idDoc) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idDoc", idDoc);
            
            deleteOrUpdateByQuery("DELETE FROM DetalleDestinatario x WHERE x.documento.id = :idDoc", parametros);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

}
