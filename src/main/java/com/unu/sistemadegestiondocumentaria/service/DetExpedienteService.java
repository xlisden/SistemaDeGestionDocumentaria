package com.unu.sistemadegestiondocumentaria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class DetExpedienteService extends Repository<DetalleDocumento> {

    private static DetExpedienteService INSTANCIA;

    private DetExpedienteService(Class<DetalleDocumento> type) {
        super(type);
    }

    public static DetExpedienteService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DetExpedienteService(DetalleDocumento.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(DetalleDocumento t) {
        try {
            Validation.validateDetExpediente(t);
            super.add(t);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<DetalleDocumento> getAll() {
        return super.getAll();
    }    

    @Override
    public DetalleDocumento getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

    public int getId(int idDoc, int idExp) {
        int id = 0;
        DetalleDocumento detDest = null;
        Map<String, Object> parametros = new HashMap<>();
        
        parametros.put("idDoc", idDoc);
        parametros.put("idExp", idExp);

        try {
            detDest = getByQuery( "SELECT x FROM DetalleDocumento x WHERE x.documento.id = :idDoc AND x.expediente.id = :idExp", parametros);
            if (detDest != null) {
                id = detDest.getId();
            }
        } catch (ValidationException e) {
            e.printMessage();
        }        
        return id;
    }

    public void deleteByDoc(int idDoc) {
        Map<String, Object> parametros = new HashMap<>();

        try {
            parametros.put("idDoc", idDoc);
            deleteOrUpdateByQuery("DELETE FROM DetalleDocumento x WHERE x.documento.id = :idDoc", parametros);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }    

}
