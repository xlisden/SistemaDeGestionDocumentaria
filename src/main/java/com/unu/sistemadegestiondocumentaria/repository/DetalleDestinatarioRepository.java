package com.unu.sistemadegestiondocumentaria.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.Administrativo;

public class DetalleDestinatarioRepository extends Repository<DetalleDestinatario> {

	private static DetalleDestinatarioRepository INSTANCIA;

	private DetalleDestinatarioRepository(Class<DetalleDestinatario> type) {
		super(type);
	}

	public static DetalleDestinatarioRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new DetalleDestinatarioRepository(DetalleDestinatario.class);
		}
		return INSTANCIA;
	}

	public int getId(int idDoc, int idDest) {
		String sql = "SELECT x FROM DetalleDestinatario x WHERE x.documento.id = :idDoc AND x.destinatario.id = :idDest";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, DetalleDestinatario.class);
		query.setParameter("idDoc", idDoc);
		query.setParameter("idDest", idDest);
		
		try {
			DetalleDestinatario detDest = (DetalleDestinatario) query.setMaxResults(1).getSingleResult();
			return detDest.getId();
		} catch (NoResultException e) {
			return -1;
		} finally {
			hc.closeConnection();
		}
	}	
	
	public void deleteByDoc(int idDoc) {
		String sql = "DELETE FROM DetalleDestinatario x WHERE x.documento.id = :idDoc";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql);
		query.setParameter("idDoc", idDoc);
		
		try {
            em.getTransaction().begin();
            query.executeUpdate();
            em.getTransaction().commit();
		} catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
		} finally {
			hc.closeConnection();
		}		
	}
	
	public List<Administrativo> getDestsByDoc(int idDoc) {
		String sql = "SELECT x FROM DetalleDestinatario x WHERE x.documento.id = :idDoc";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, DetalleDestinatario.class);
		query.setParameter("idDoc", idDoc);
		
		try {
			List<Administrativo> destinatarios = new ArrayList<Administrativo>();
			List<DetalleDestinatario> detalle = query.getResultList();
			for(DetalleDestinatario detdoc : detalle) {
				destinatarios.add(detdoc.getDestinatario());
			}
			return destinatarios;
		} catch (Exception e) {
			return Collections.emptyList();
		} finally {
			hc.closeConnection();
		}
	}	
	
}
