package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.repository.IDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.IEstadoRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;

public class DocumentoRepository implements IDocumentoRepository {

    private HibernateConfig hc = new HibernateConfig();
    private IEstadoRepository estadoRepository = new EstadoRepository();
    private Validation validaciones = new Validation();
    private EntityManager em;

    @Override
    public void addDocumento(Documento documento) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateDocumento(documento);
            documento.setCorrelativo(setCorrelativo());
            em.persist(documento);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateDocumento(int id, Documento documento) {
        em = hc.getEntityManager();
        Documento doc = getByIdDocumento(id);
        em.getTransaction().begin();
        try {
            validaciones.validateDocumento(documento);
            setDocumento(doc, documento);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteDocumento(int id) {
        em = hc.getEntityManager();
        Documento doc = getByIdDocumento(id);
        em.getTransaction().begin();
        em.remove(doc);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<Documento> getAllDocumentos() {
        em = hc.getEntityManager();
        List<Documento> documentos = em.createQuery("SELECT doc FROM Documento doc", Documento.class).getResultList();
        hc.closeConnection();
        return documentos;
    }

    @Override
    public Documento getByIdDocumento(int id) {
        em = hc.getEntityManager();
        Documento doc = em.find(Documento.class, id);
        hc.closeConnection();
        try {
            if (doc == null) {
                throw new ValidationException(Validation.warningColor + "El Documento no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return doc;
    }

    @Override
    public void updateEstadoDocumento(int id) {
        em = hc.getEntityManager();
        Documento doc = getByIdDocumento(id);
        em.getTransaction().begin();
        try {
            setEntregado(doc);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    private String setCorrelativo() {
        Documento doc = em.createQuery("SELECT doc FROM Documento doc ORDER BY doc.id", Documento.class).getResultList().getLast();
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

    private void setEntregado(Documento documento) {
        documento.setEstado(estadoRepository.getByNombre("ENTREGADO"));
    }

}
