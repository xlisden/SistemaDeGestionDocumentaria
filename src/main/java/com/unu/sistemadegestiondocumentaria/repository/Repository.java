package com.unu.sistemadegestiondocumentaria.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
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
        System.out.println(Validation.infoColor + "constructor Repository" + Validation.normalColor);
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
        if(t == null){
            throw new ValidationException(showWarning("El " + className + " " + id + " no existe en la base de datos."));
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        hc.closeConnection();
    }

    public List<T> getAll() {
        System.out.println(Validation.infoColor + "get all in repository" + Validation.normalColor);
        em = hc.getEntityManager();
        List<T> tList = em.createQuery("SELECT x FROM " + className + " x", typeClass).getResultList();
        hc.closeConnection();
        return tList;
    }

    public T getById(int id) {
        em = hc.getEntityManager();
        T t = em.find(typeClass, id);
        hc.closeConnection();
        if(t == null){
            throw new ValidationException(showWarning("El " + className + " " + id+ " no existe en la base de datos."));
        }        
        return t;
    }

    public int getLastId(){
        int id = -1;
        em = hc.getEntityManager();
        id = em.createQuery("SELECT x.id FROM " + className + " x ORDER BY x.id DESC", Integer.class).setMaxResults(1).getSingleResult();
        hc.closeConnection();
        return id;
    }  
    
    public T getLast(){
        T t = null;
        em = hc.getEntityManager();
        t = em.createQuery("SELECT x FROM " + className + " x ORDER BY x.id DESC", typeClass).setMaxResults(1).getSingleResult();
        hc.closeConnection();
        return t;
    }

}
