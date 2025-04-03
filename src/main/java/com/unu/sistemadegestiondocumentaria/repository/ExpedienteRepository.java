package com.unu.sistemadegestiondocumentaria.repository;

import javax.persistence.NoResultException;

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
	
}
