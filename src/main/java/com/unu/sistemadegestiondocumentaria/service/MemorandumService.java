package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Memorandum;

import java.util.ArrayList;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.repository.MemorandumRepository;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.HashMap;
import java.util.Map;

public class MemorandumService {

    private final DocumentoService docService = DocumentoService.instanciar();

    private MemorandumRepository memoRepository;

    private static MemorandumService INSTANCIA;

    private MemorandumService() {
        memoRepository = MemorandumRepository.instanciar();
    }

    public static MemorandumService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new MemorandumService();
        }
        return INSTANCIA;
    }

    public void add(Memorandum t) {
        try {
//            Documento doc = t.getDocumento();
//            if (doc == null) {
//                return;
//            }
//            boolean isCorrect = docService.addDoc(doc);
//            if (isCorrect) {
//                Validation.validateMemorandum(t);
//                super.add(t);
//            }
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void update(int id, Memorandum t) {
        try {
//            Memorandum memorandum = getById(id);
//            if (memorandum == null) {
//                // throw new ValidationException(Validation.showWarning("El Memorandum no puede estar vacío."));
//                return;
//            }
//
//            int idDoc = memorandum.getDocumento().getId();
//            Documento doc = docService.getById(idDoc);
//            if (doc == null) {
//                return;
//            }
//
////            doc = docService.setDocumento(doc, t.getDocumento());
////          no se porque validar, si ya documento validara en update
//            t.setDocumento(doc);
//
//            Validation.validateMemorandum(t);
//            docService.update(idDoc, doc);
//            memorandum.setAsunto(t.getAsunto());
//            memorandum.setReferencia(t.getReferencia());
//
//            super.update(id, memorandum);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // borrar desde aqui porque en docservice se tendria que instaciar memorandumService creando un bucle
    public void delete(int id) {
        try {
//            Memorandum memorandum = getById(id);
//            if (memorandum == null) {
//                // throw new ValidationException(Validation.showWarning("El Memorandum no puede estar vacío."));
//                return;
//            }
//            docService.deleteDocDependencias(memorandum.getDocumento().getId());
//
//            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public Memorandum getById(int id) {
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
//            Memorandum memorandum = getById(id);
//            if (memorandum == null) {
//                // throw new ValidationException(Validation.showWarning("El Memorandum no puede estar vacío."));
//                return;
//            }
//
//            int idDoc = memorandum.getDocumento().getId();
//            docService.updateEstadoDocumento(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    // ¿
    public List<Administrativo> getDestinatarios(int id) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
//            Memorandum memorandum = getById(id);
//            if (memorandum == null) {
//                return null;
//            }
//
//            int idDoc = memorandum.getDocumento().getId();
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
        return memoRepository.getAsunto(idDoc);
    }

}
