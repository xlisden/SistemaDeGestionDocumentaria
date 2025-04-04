package com.unu.sistemadegestiondocumentaria.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;

public class AdministrativoRepository extends Repository<Administrativo> {

	private static AdministrativoRepository INSTANCIA;

	private AdministrativoRepository(Class<Administrativo> type) {
		super(type);
	}

	public static AdministrativoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new AdministrativoRepository(Administrativo.class);
		}
		return INSTANCIA;
	}
	
	public List<Administrativo> getAllAdminOrdenAlfNombre(){
		String sql = "SELECT x FROM Administrativo x ORDER BY x.persona.nombre ASC";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, Administrativo.class);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		} finally {
			hc.closeConnection();
		}
	}
	
	public List<Administrativo> getAllAdminOrdenAlfApPaterno(){
		String sql = "SELECT x FROM Administrativo x ORDER BY x.persona.apellidoPaterno ASC";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, Administrativo.class);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		} finally {
			hc.closeConnection();
		}
	}	
}
