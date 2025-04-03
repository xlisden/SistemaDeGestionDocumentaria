package com.unu.sistemadegestiondocumentaria.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.entity.Estado;

public class EstadoRepository extends Repository<Estado> {

	private static EstadoRepository INSTANCIA;

	private EstadoRepository(Class<Estado> type) {
		super(type);
	}

	public static EstadoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new EstadoRepository(Estado.class);
		}
		return INSTANCIA;
	}

	public Estado getByNombre(String nombre) {
		String sql = "SELECT x FROM Estado x WHERE x.nombre = :nombre";

		em = hc.getEntityManager();
		Query query = em.createQuery(sql, Estado.class);
		query.setParameter("nombre", nombre.toUpperCase());

		try {
			return (Estado) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			hc.closeConnection();
		}			
	}

}