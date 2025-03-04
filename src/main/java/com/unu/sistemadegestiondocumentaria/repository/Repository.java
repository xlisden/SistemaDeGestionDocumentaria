package com.unu.sistemadegestiondocumentaria.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.magentaColor;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.normalColor;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class Repository<T> {

    private Class<T> typeClass;
    private HibernateConfig hc = new HibernateConfig();
    private EntityManager em;
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
        System.out.println(magentaColor + "update de repository" + normalColor);
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
        if(t == null){
            throw new ValidationException(showWarning("El " + className + " " + id + " no existe en la base de datos."));
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    public List<T> getAll(String query) {
        em = hc.getEntityManager();
        List<T> tList = em.createQuery(query, typeClass).getResultList();
        hc.closeConnection();
        return tList;
    }

    public T getById(int id) {
        System.out.println(magentaColor + "get by id de repository" + normalColor);
        em = hc.getEntityManager();
        T t = em.find(typeClass, id);
        hc.closeConnection();
        if(t == null){
            throw new ValidationException(showWarning("El " + className + " " + id+ " no existe en la base de datos."));
        }        
        return t;
    }

}
