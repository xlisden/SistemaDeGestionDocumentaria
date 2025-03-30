package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.ActaSustentacionTesis;

import java.util.ArrayList;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.HashMap;
import java.util.Map;

public class ActaSustService extends Repository<ActaSustentacionTesis> {

    private final DocumentoService docService = DocumentoService.instanciar();

    private static ActaSustService INSTANCIA;

    private ActaSustService(Class<ActaSustentacionTesis> type) {
        super(type);
    }

    public static ActaSustService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new ActaSustService(ActaSustentacionTesis.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(ActaSustentacionTesis t) {
        try {
            Documento doc = t.getDocumento();
            if (doc == null) {
                return;
            }
            boolean isCorrect = docService.addDoc(doc);
            if (isCorrect) {
                Validation.validateActaSust(t);
                super.add(t);
            }
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public void update(int id, ActaSustentacionTesis t) {
        try {
            ActaSustentacionTesis acta = getById(id);
            if (acta == null) {
                // throw new ValidationException(Validation.showWarning("El ActaSustentacionTesis no puede estar vacío."));
                return;
            }

            int idDoc = acta.getDocumento().getId();
            Documento doc = docService.getById(idDoc);
            if (doc == null) {
                return;
            }

//            doc = docService.setDocumento(doc, t.getDocumento());
//          no se porque validar, si ya documento validara en update
            t.setDocumento(doc);

            Validation.validateActaSust(t);
            docService.update(idDoc, doc);
            acta.setTema(t.getTema());
            acta.setCalificacion(t.getCalificacion());

            super.update(id, acta);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // borrar desde aqui porque en docservice se tendria que instaciar actaService creando un bucle
    @Override
    public void delete(int id) {
        try {
            ActaSustentacionTesis acta = getById(id);
            if (acta == null) {
                // throw new ValidationException(Validation.showWarning("El ActaSustentacionTesis no puede estar vacío."));
                return;
            }
            docService.deleteDocDependencias(acta.getDocumento().getId());

            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public ActaSustentacionTesis getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }
    
    // ¿
    public void updateEstadoDocumento(int id) {
        try {
            ActaSustentacionTesis acta = getById(id);
            if (acta == null) {
                // throw new ValidationException(Validation.showWarning("El ActaSustentacionTesis no puede estar vacío."));
                return;
            }

            int idDoc = acta.getDocumento().getId();
            docService.updateEstadoDocumento(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // ¿
    public List<Administrativo> getDestinatarios(int id) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
            ActaSustentacionTesis acta = getById(id);
            if (acta == null) {
                return null;
            }

            int idDoc = acta.getDocumento().getId();
            if (docService.getById(idDoc) == null) {
                return null;
            }
            destinatarios = docService.getDestinatarios(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return destinatarios;
    }

    public String getTemaByDoc(int idDoc) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idDoc", idDoc);

        ActaSustentacionTesis acta = getByQuery("SELECT x FROM ActaSustentacionTesis x WHERE x.documento.id = :idDoc", parametros);
        return acta.getTema();
    }

}
