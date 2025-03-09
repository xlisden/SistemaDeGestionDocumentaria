package com.unu.sistemadegestiondocumentaria.service;

import java.time.LocalDate;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class DocumentoService extends Repository<Documento> {

    private EstadoService estadoService = new EstadoService(Estado.class);

    public DocumentoService(Class<Documento> type) {
        super(type);
    }

    @Override
    public void add(Documento t) {
        try {
            Validation.validateDocumento(t);
            t.setCorrelativo(setCorrelativo());
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Documento t) {
        try {
            Validation.validateDocumento(t);
            Documento doc = getById(id);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }
            if (doc.getCorrelativo() == null || doc.getCorrelativo().isBlank()) {
                throw new ValidationException(Validation.showWarning("El correlativo delsss Documento no puede estar vacío."));
            }
//            doc.setCorrelativo(t.getCorrelativo());
//            doc.setFechaEmision(t.getFechaEmision());
//            doc.setTipoDocumento(t.getTipoDocumento());
//            doc.setEstado(t.getEstado());
//            doc.setEmisor(t.getEmisor());
            setDocumento(doc, t);
            super.update(id, doc);
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

    public List<Documento> getAll() {
        return super.getAll();
    }

    @Override
    public Documento getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

    public void updateEstadoDocumento(int id) {
        try {
            Documento doc = getById(id);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }
            doc.setEstado(estadoService.getByNombre("ENTREGADO"));
            super.update(id, doc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    private String setCorrelativo() {
        Documento doc = null;
        if (getAll().isEmpty()) {
            return "001";
        } else {
            doc = super.getLast();
            System.out.println(Validation.magentaColor + "A. Emision = " + doc.getFechaEmision().getYear() + Validation.normalColor);
            System.out.println(Validation.magentaColor + "A. Actual = " + LocalDate.now().getYear() + Validation.normalColor);
            if (doc.getFechaEmision().getYear() + 1900 < LocalDate.now().getYear()) {
                return "001";
            } else {
                int c = Integer.parseInt(doc.getCorrelativo()) + 1;
                return c + "";
            }
        }
    }

    private void setDocumento(Documento documento, Documento doc) {
        documento.setCorrelativo(doc.getCorrelativo());
        documento.setFechaEmision(doc.getFechaEmision());
        documento.setTipoDocumento(doc.getTipoDocumento());
        documento.setEstado(doc.getEstado());
        documento.setEmisor(doc.getEmisor());
    }

}
