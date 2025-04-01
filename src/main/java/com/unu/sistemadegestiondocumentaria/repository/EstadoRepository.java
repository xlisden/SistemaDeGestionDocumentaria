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
		Query query = null;
		String sql = "SELECT est FROM Estado est WHERE est.nombre = :nombre";

		em = hc.getEntityManager();
		query = em.createQuery(sql, Estado.class);
		query.setParameter("nombre", nombre);

		try {
			return (Estado) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			hc.closeConnection();
		}		
	}

}
/*
 * getByQuery("SELECT x FROM ActaSustentacionTesis x WHERE x.documento.id = :idDoc"
 * , parametros)
 * 
 * public T getByQuery(String query, Map<String, Object> parameters) { T t =
 * null; Query q = null;
 * 
 * em = hc.getEntityManager(); q = em.createQuery(query, typeClass);
 * 
 * if (!parameters.isEmpty()) { for (String key : parameters.keySet()) {
 * q.setParameter(key, parameters.get(key)); } }
 * 
 * List<T> resultados = q.getResultList(); if (!resultados.isEmpty()) { t =
 * resultados.get(0); }
 * 
 * hc.closeConnection(); return t; }
 */