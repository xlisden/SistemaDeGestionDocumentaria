package com.unu.sistemadegestiondocumentaria.repository.impl;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.ITipoDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class TipoDocumentoRepository implements ITipoDocumentoRepository {

    private HibernateConfig hc = new HibernateConfig();
    private Validation validaciones = new Validation();
    private EntityManager em;

    @Override
    public void addTipoDocumento(TipoDocumento tipoDocumento) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        try {
            validaciones.validateTipoDocumento(tipoDocumento);
            em.persist(tipoDocumento);
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void updateTipoDocumento(int id, TipoDocumento tipoDocumento) {
        em = hc.getEntityManager();
        TipoDocumento td = getByIdTipoDocumento(id);
        em.getTransaction().begin();
        try {
            validaciones.validateTipoDocumento(tipoDocumento);
            td.setNombre(tipoDocumento.getNombre());
            em.getTransaction().commit();
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } finally {
            hc.closeConnection();
        }
    }

    @Override
    public void deleteTipoDocumento(int id) {
        em = hc.getEntityManager();
        TipoDocumento td = getByIdTipoDocumento(id);
        em.getTransaction().begin();
        em.remove(td);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    @Override
    public List<TipoDocumento> getAllTipoDocumentos() {
        em = hc.getEntityManager();
        List<TipoDocumento> tiposdocumento = em.createQuery("SELECT td FROM TipoDocumento td", TipoDocumento.class).getResultList();
        hc.closeConnection();
        return tiposdocumento;
    }

    @Override
    public TipoDocumento getByIdTipoDocumento(int id) {
        em = hc.getEntityManager();
        TipoDocumento td = em.find(TipoDocumento.class, id);
        hc.closeConnection();
        try {
            if (td == null) {
                throw new ValidationException(Validation.warningColor + "El Tipo de Documento no ha sido encontrado." + Validation.normalColor);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
        return td;
    }

}
