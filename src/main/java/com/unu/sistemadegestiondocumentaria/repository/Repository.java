package com.unu.sistemadegestiondocumentaria.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.validations.*;

public abstract class Repository<T> {

	private Class<T> typeClass;
    protected final HibernateConfig hc = HibernateConfig.instanciar();
    protected EntityManager em;
    private String className = "";

    public Repository(Class<T> type) {
        this.typeClass = type;
        className = typeClass.getName().substring(44, typeClass.getName().length());
    }

    public void add(T t) {
        em = hc.getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    public void update(int id, T t) {
        T tAux = getById(id);
        em = hc.getEntityManager();
        tAux = t;
        em.merge(tAux);
        em.getTransaction().begin();
        em.getTransaction().commit();
        hc.closeConnection();
    }

    public void delete(int id) {
        em = hc.getEntityManager();
        T t = em.find(typeClass, id);
        if (t == null) {
            throw new ValidationException(Validation.showWarning("El " + className + " " + id + " no existe en la base de datos."));
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    public List<T> getAll() {
        em = hc.getEntityManager();
        List<T> tList = em.createQuery("SELECT x FROM " + className + " x", typeClass).getResultList();
        hc.closeConnection();
        return tList;
    }

    public T getById(int id) {
        em = hc.getEntityManager();
        T t = em.find(typeClass, id);
        hc.closeConnection();
        if (t == null) {
            throw new ValidationException(Validation.showWarning("El " + className + " " + id + " no existe en la base de datos."));
        }
        return t;
    }

    public int getLastId() {
        int id = -1;
        em = hc.getEntityManager();
        id = em.createQuery("SELECT x.id FROM " + className + " x ORDER BY x.id DESC", Integer.class).setMaxResults(1).getSingleResult();
        hc.closeConnection();
        return id;
    }

    public T getLast() {
        T t = null;
        em = hc.getEntityManager();
        t = em.createQuery("SELECT x FROM " + className + " x ORDER BY x.id DESC", typeClass).setMaxResults(1).getSingleResult();
        hc.closeConnection();
//        List<T> list = getAll();
//        if (!list.isEmpty()) {
//            t = list.getLast();
//        }
        return t;
    }

    public T getByQuery(String query, Map<String, Object> parameters) {
        T t = null;
        Query q = null;

        em = hc.getEntityManager();
        q = em.createQuery(query, typeClass);

        if (!parameters.isEmpty()) {
            for (String key : parameters.keySet()) {
                q.setParameter(key, parameters.get(key));
            }
        }

        List<T> resultados = q.getResultList();
        if (!resultados.isEmpty()) {
            t = resultados.get(0);
        }

        hc.closeConnection();
        return t;
    }

    public int deleteOrUpdateByQuery(String query, Map<String, Object> parameters) {
        int filas = 0;
        Query q = null;

        em = hc.getEntityManager();
        q = em.createQuery(query);

        if (!parameters.isEmpty()) {
            for (String key : parameters.keySet()) {
                q.setParameter(key, parameters.get(key));
            }
        }

        try {
            em.getTransaction().begin();
            filas = q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        hc.closeConnection();
        return filas;
    }

}
