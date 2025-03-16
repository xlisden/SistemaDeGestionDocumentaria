package com.unu.sistemadegestiondocumentaria.service;

import java.util.HashMap;
import java.util.Map;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
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

    public void update(DetalleDocumento t, Expediente exp) {
        try {
            int id = getId(t.getDocumento().getId(), exp.getId());
            DetalleDocumento detExp = getById(id);
            if (detExp == null) {
                // throw new ValidationException(Validation.showWarning("El Det. Expediente no puede estar vacío."));
                return;
            }
            
            Validation.validateDetExpediente(t);
            detExp.setExpediente(exp);
            
            super.update(id, detExp);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void delete(int idDoc, int idExp) {
        try {
            int id = getId(idDoc, idExp);
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
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
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idDoc", idDoc);
            parametros.put("idExp", idExp);

            DetalleDocumento detDest = getByQuery("SELECT x FROM DetalleDocumento x WHERE x.documento.id = :idDoc AND x.expediente.id = :idExp", parametros);
            if (detDest == null) {
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

            deleteOrUpdateByQuery("DELETE FROM DetalleDocumento x WHERE x.documento.id = :idDoc", parametros);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

}
