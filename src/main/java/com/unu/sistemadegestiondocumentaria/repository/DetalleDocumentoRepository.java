package com.unu.sistemadegestiondocumentaria.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class DetalleDocumentoRepository extends Repository<DetalleDocumento> {

	private static DetalleDocumentoRepository INSTANCIA;

	private DetalleDocumentoRepository(Class<DetalleDocumento> type) {
		super(type);
	}

	public static DetalleDocumentoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new DetalleDocumentoRepository(DetalleDocumento.class);
		}
		return INSTANCIA;
	}
	
	public int getId(int idDoc, int idExp) {
		String sql = "SELECT x FROM DetalleDocumento x WHERE x.documento.id = :idDoc AND x.expediente.id = :idExp";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, DetalleDocumento.class);
		query.setParameter("idDoc", idDoc);
		query.setParameter("idExp", idExp);
		
		try {
			DetalleDocumento detDoc = (DetalleDocumento) query.getSingleResult();
			return detDoc.getId();
		} catch (NoResultException e) {
			return -1;
		} finally {
			hc.closeConnection();
		}
	}
	
	public void deleteByDoc(int idDoc) {
		String sql = "DELETE FROM DetalleDocumento x WHERE x.documento.id = :idDoc";
		
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
	
	public List<Expediente> getExpedientesByDoc(int idDoc) {
		String sql = "SELECT x FROM DetalleDocumento x WHERE x.documento.id = :idDoc";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, DetalleDocumento.class);
		query.setParameter("idDoc", idDoc);
		
		try {
			List<Expediente> expedientes = new ArrayList<Expediente>();
			List<DetalleDocumento> detalle = query.getResultList();
			for(DetalleDocumento detdoc : detalle) {
				expedientes.add(detdoc.getExpediente());
			}
			return expedientes;
		} catch (NoResultException e) {
			return Collections.emptyList();
		} finally {
			hc.closeConnection();
		}
	}
	
	public List<Documento> getDocsByExp(int idExp) {
		String sql = "SELECT x FROM DetalleDocumento x WHERE x.expediente.id = :idExp";
		
		em = hc.getEntityManager();
		Query query = em.createQuery(sql, DetalleDocumento.class);
		query.setParameter("idExp", idExp);
		
		try {

			List<Documento> documentos = new ArrayList<Documento>();
			List<DetalleDocumento> detalle = query.getResultList();
			for(DetalleDocumento detdoc : detalle) {
				documentos.add(detdoc.getDocumento());
			}
			return documentos;
		} catch (NoResultException e) {
			return Collections.emptyList();
		} finally {
			hc.closeConnection();
		}
	}	
}
