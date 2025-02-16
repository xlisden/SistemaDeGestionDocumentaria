package com.unu.sistemadegestiondocumentaria.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConfig {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public HibernateConfig() {
        this.factory = Persistence.createEntityManagerFactory("sistema_gestion_documentaria_pu");
    }

    public EntityManager getEntityManager() {
        return entityManager = new HibernateConfig().getFactory().createEntityManager();
    }

    public void closeConnection() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            getFactory().close();
        }
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

}
