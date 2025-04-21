package com.unu.sistemadegestiondocumentaria.repository;

import javax.persistence.NoResultException;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import java.util.List;
import javax.persistence.Query;

public class DocumentoRepository extends Repository<Documento> {

    private static DocumentoRepository INSTANCIA;

    private DocumentoRepository(Class<Documento> type) {
        super(type);
    }

    public static DocumentoRepository instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DocumentoRepository(Documento.class);
        }
        return INSTANCIA;
    }

    public Documento getLast() {
        em = hc.getEntityManager();

        try {
            return em.createQuery("SELECT x FROM Documento x ORDER BY x.id DESC", Documento.class).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            hc.closeConnection();
        }
    }

    public int getIdByNombre(String nombre) {
        String sql = "SELECT x.id FROM Documento x WHERE x.tipoDocumento.id = :idTipoDoc AND x.correlativo = :correlativo";

        String tipoDoc = nombre.substring(0, nombre.length() - 4);
        String correlativo = nombre.substring(nombre.length() - 3, nombre.length());
        int idTipoDoc = 0;

        switch (tipoDoc) {
            case "OFICIO":
                idTipoDoc = 1;
                break;
            case "MEMORÁNDUM":
                idTipoDoc = 2;
                break;
            case "ACTAS DE SUSTENTACIÓN DE TESIS":
                idTipoDoc = 3;
                break;
            default:
                idTipoDoc = 0;
                break;
        }

        em = hc.getEntityManager();

        Query query = em.createQuery(sql, Integer.class);
        query.setParameter("idTipoDoc", idTipoDoc);
        query.setParameter("correlativo", correlativo);

        try {
            return (Integer) query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } finally {
            hc.closeConnection();
        }
    }

    public List<Documento> getAllPorTipoDoc(int idTipoDoc) {
        em = hc.getEntityManager();
        List<Documento> tList = em.createQuery("SELECT x FROM Documento x WHERE x.tipoDocumento.id = " + idTipoDoc, Documento.class).getResultList();
        hc.closeConnection();
        return tList;
    }
    
    public List<Documento> getAllPendientes() {
        em = hc.getEntityManager();
        List<Documento> tList = em.createQuery("SELECT x FROM Documento x WHERE x.estado.id = 1", Documento.class).getResultList();
        hc.closeConnection();
        return tList;
    }    
}
