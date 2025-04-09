package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.ActaSustentacionTesis;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ActaRepository extends Repository<ActaSustentacionTesis> {

    private static ActaRepository INSTANCIA;

    private ActaRepository(Class<ActaSustentacionTesis> type) {
        super(type);
    }

    public static ActaRepository instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new ActaRepository(ActaSustentacionTesis.class);
        }
        return INSTANCIA;
    }

    public String getAsunto(int idDoc) {
        em = hc.getEntityManager();
        try {
            Query query = em.createQuery("SELECT x.tema FROM ActaSustentacionTesis x WHERE x.documento.id = :idDoc", String.class);
            query.setParameter("idDoc", idDoc);
            return (String) query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return "";
        } finally {
            hc.closeConnection();
        }
    }

}
