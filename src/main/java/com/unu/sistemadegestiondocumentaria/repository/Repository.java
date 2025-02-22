package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;

public class Repository<T> {

    private Class<T> typeClass;
    private HibernateConfig hc = new HibernateConfig();
    private EntityManager em;

    public Repository(Class<T> type) {
        this.typeClass = type;
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
            throw new ValidationException(showWarning("El Grado de Instrucción no puede estar vacío."));
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
        em = hc.getEntityManager();
        T t = em.find(typeClass, id);
        hc.closeConnection();
        if(t == null){
            throw new ValidationException(showWarning("El Grado de Instrucción no puede estar vacío."));
        }        
        return t;
    }

}
