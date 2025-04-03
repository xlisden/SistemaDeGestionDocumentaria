package com.unu.sistemadegestiondocumentaria.repository;

import javax.persistence.NoResultException;

import com.unu.sistemadegestiondocumentaria.entity.Persona;

public class PersonaRepository extends Repository<Persona> {

	private static PersonaRepository INSTANCIA;

	private PersonaRepository(Class<Persona> type) {
		super(type);
	}

	public static PersonaRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new PersonaRepository(Persona.class);
		}
		return INSTANCIA;
	}

	public int getLastId() {
		em = hc.getEntityManager();
		
		try {
			return em.createQuery("SELECT x.id FROM Persona x ORDER BY x.id DESC", Integer.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return -1;
		} finally {
			hc.closeConnection();
		}			
	}
	
    public Persona getLast() {
        em = hc.getEntityManager();

		try {
			return em.createQuery("SELECT x FROM Persona x ORDER BY x.id DESC", Persona.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			hc.closeConnection();
		}			
    }	
}
