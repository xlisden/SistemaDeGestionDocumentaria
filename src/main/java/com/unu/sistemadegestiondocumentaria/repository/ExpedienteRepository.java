package com.unu.sistemadegestiondocumentaria.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.entity.Expediente;

public class ExpedienteRepository extends Repository<Expediente> {

    private static ExpedienteRepository INSTANCIA;

    private ExpedienteRepository(Class<Expediente> type) {
        super(type);
    }

    public static ExpedienteRepository instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new ExpedienteRepository(Expediente.class);
        }
        return INSTANCIA;
    }

    public int getLastId() {
        em = hc.getEntityManager();

        try {
            return em.createQuery("SELECT x.id FROM Expediente x ORDER BY x.id DESC", Integer.class).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return -1;
        } finally {
            hc.closeConnection();
        }
    }

    public Expediente getLast() {
        em = hc.getEntityManager();

        try {
            return em.createQuery("SELECT x FROM Expediente x ORDER BY x.id DESC", Expediente.class).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            hc.closeConnection();
        }
    }

    public List<Expediente> getAllExpOrdenAlfNombre() {
        String sql = "SELECT x FROM Expediente x ORDER BY x.persona.nombre ASC";

        em = hc.getEntityManager();
        Query query = em.createQuery(sql, Expediente.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            hc.closeConnection();
        }
    }

    public List<Expediente> getAllExpOrdenAlfApPaterno() {
        String sql = "SELECT x FROM Expediente x ORDER BY x.persona.apellidoPaterno ASC";

        em = hc.getEntityManager();
        Query query = em.createQuery(sql, Expediente.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            hc.closeConnection();
        }
    }

}
