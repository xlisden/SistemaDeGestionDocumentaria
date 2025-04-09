package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Memorandum;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class MemorandumRepository extends Repository<Memorandum> {

    private static MemorandumRepository INSTANCIA;

    private MemorandumRepository(Class<Memorandum> type) {
        super(type);
    }

    public static MemorandumRepository instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new MemorandumRepository(Memorandum.class);
        }
        return INSTANCIA;
    }

    public String getAsunto(int idDoc) {
        em = hc.getEntityManager();
        try {
            Query query = em.createQuery("SELECT x.asunto FROM Memorandum x WHERE x.documento.id = :idDoc", String.class);
            query.setParameter("idDoc", idDoc);
            return (String) query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return "";
        } finally {
            hc.closeConnection();
        }
    }

}
