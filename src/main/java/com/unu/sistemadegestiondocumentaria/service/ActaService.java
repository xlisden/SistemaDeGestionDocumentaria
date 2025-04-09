package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Oficio;

import java.util.ArrayList;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.repository.ActaRepository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.Collections;

public class ActaService {

    private static ActaService INSTANCIA;
    
    private final DocumentoService docService = DocumentoService.instanciar();
    private ActaRepository actaRepository ;


    private ActaService() {
        actaRepository = ActaRepository.instanciar();
    }

    public static ActaService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new ActaService();
        }
        return INSTANCIA;
    }

    
    public void add(Oficio t) {
        try {
            Documento doc = t.getDocumento();
            if (doc == null) {
                return;
            }
//            boolean isCorrect = docService.addDoc(doc);
//            if (isCorrect) {
//                Validation.validateOficio(t);
//                super.add(t);
//            }
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    
    public void update(int id, Oficio t) {
        try {
//            Oficio oficio = getById(id);
//            if (oficio == null) {
//                // throw new ValidationException(Validation.showWarning("El Oficio no puede estar vacío."));
//                return;
//            }
//
//            int idDoc = oficio.getDocumento().getId();
////            Documento doc = docService.getById(idDoc);
//            if (doc == null) {
//                return;
//            }
//
////            doc = docService.setDocumento(doc, t.getDocumento());
////          no se porque validar, si ya documento validara en update
//            t.setDocumento(doc);
//
//            Validation.validateOficio(t);
//            docService.update(idDoc, doc);
//            oficio.setAsunto(t.getAsunto());
//            oficio.setReferencia(t.getReferencia());

//            super.update(id, oficio);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // borrar desde aqui porque en docservice se tendria que instaciar oficioService creando un bucle
    
    public void delete(int id) {
        try {
//            Oficio oficio = getById(id);
//            if (oficio == null) {
//                // throw new ValidationException(Validation.showWarning("El Oficio no puede estar vacío."));
//                return;
//            }
//            docService.deleteDocDependencias(oficio.getDocumento().getId());
//
//            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    
    public Oficio getById(int id) {
        try {
//            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }
    
    // ¿
    public void updateEstadoDocumento(int id) {
        try {
//            Oficio oficio = getById(id);
//            if (oficio == null) {
//                // throw new ValidationException(Validation.showWarning("El Oficio no puede estar vacío."));
//                return;
//            }
//
//            int idDoc = oficio.getDocumento().getId();
//            docService.updateEstadoDocumento(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // ¿
    public List<Administrativo> getDestinatarios(int id) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
//            Oficio oficio = getById(id);
//            if (oficio == null) {
//                return null;
//            }
//
//            int idDoc = oficio.getDocumento().getId();
//            if (docService.getById(idDoc) == null) {
//                return null;
//            }
//            destinatarios = docService.getDestinatarios(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return destinatarios;
    }

    public String getAsuntoByDoc(int idDoc) {
        return actaRepository.getAsunto(idDoc);
    }
    
    public List<Oficio> getAll(){
        return Collections.emptyList();
    }

}
