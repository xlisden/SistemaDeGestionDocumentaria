package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class OficioRepository extends Repository<Oficio> {

    private static OficioRepository INSTANCIA;

    private OficioRepository(Class<Oficio> type) {
        super(type);
    }

    public static OficioRepository instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new OficioRepository(Oficio.class);
        }
        return INSTANCIA;
    }

    public String getAsunto(int idDoc) {
        em = hc.getEntityManager();
        try {
            Query query = em.createQuery("SELECT x.asunto FROM Oficio x WHERE x.documento.id = :idDoc", String.class);
            query.setParameter("idDoc", idDoc);
            return (String) query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return "";
        } finally {
            hc.closeConnection();
        }
    }

}
