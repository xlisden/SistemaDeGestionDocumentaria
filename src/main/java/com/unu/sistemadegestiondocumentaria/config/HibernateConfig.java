package com.unu.sistemadegestiondocumentaria.config;

import com.unu.sistemadegestiondocumentaria.service.AdministrativoService;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateConfig {

    private static HibernateConfig INSTANCIA;

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    private HibernateConfig() {
        this.factory = Persistence.createEntityManagerFactory("sistema_gestion_documentaria_pu");
    }

    public static HibernateConfig instanciar() {
//        if (INSTANCIA == null) {
        INSTANCIA = new HibernateConfig();
//        }
        return INSTANCIA;
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

    public static void instanciarServicios() {
        System.out.println("wawawawawawawa");
        new Thread(() -> DocumentoService.instanciar()).start();
        new Thread(() -> ExpedienteService.instanciar()).start();
        new Thread(() -> AdministrativoService.instanciar()).start();
//        DocumentoService docService = DocumentoService.instanciar();
//        ExpedienteService expService = ExpedienteService.instanciar();
//        AdministrativoService adService = AdministrativoService.instanciar();
    }

}
