package com.unu.sistemadegestiondocumentaria.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class DocumentoService extends Repository<Documento> {

    private Validation validaciones = new Validation();
    private EstadoService estadoService = new EstadoService(Estado.class);
    private HibernateConfig hc = new HibernateConfig();
    private EntityManager em;

    public DocumentoService(Class<Documento> type) {
        super(type);
    }

    @Override
    public void add(Documento t) {
        try {
            validaciones.validateDocumento(t);
            t.setCorrelativo(setCorrelativo());
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Documento t) {
        try {
            validaciones.validateDocumento(t);
            Documento doc = getById(id);
            if (doc == null) {
                throw new ValidationException(showWarning("El Documento no puede estar vacío."));
            }
            if(doc.getCorrelativo() == null || doc.getCorrelativo().isBlank()){
                throw new ValidationException(showWarning("El correlativo delsss Documento no puede estar vacío."));
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
                throw new ValidationException(showWarning("El Documento no puede estar vacío."));
            }
            doc.setEstado(estadoService.getByNombre("ENTREGADO"));
            super.update(id, doc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public String setCorrelativo() {
        Documento doc = em.createQuery("SELECT doc FROM Documento doc ORDER BY doc.id DESC", Documento.class).setMaxResults(1).getSingleResult();
        
        //si la lista esta vacia 001
        if (doc == null) {
            return "001";
        } else {
            System.out.println(Validation.infoColor + "A. Emision = " + doc.getFechaEmision().getYear() + Validation.normalColor);
            System.out.println(Validation.infoColor + "A. Actual = " + LocalDate.now().getYear() + Validation.normalColor);
        }
        
        //si el anio del ultimo doc es menor al anio actual 001
        if (doc.getFechaEmision().getYear() + 1900 < LocalDate.now().getYear()) {
            return "001";
        } else {
            //si no que sume
            int c = Integer.parseInt(doc.getCorrelativo());
            c++;
            return c + "";
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
