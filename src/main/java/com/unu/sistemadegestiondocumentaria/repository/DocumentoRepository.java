package com.unu.sistemadegestiondocumentaria.repository;

import javax.persistence.NoResultException;

import com.unu.sistemadegestiondocumentaria.entity.Documento;

public class DocumentoRepository extends Repository<Documento> {

	private static DocumentoRepository INSTANCIA;

	private DocumentoRepository(Class<Documento> type) {
		super(type);
	}
	
	public static DocumentoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new DocumentoRepository(Documento.class);
		}
		return INSTANCIA;
	}

    public Documento getLast() {
        em = hc.getEntityManager();

		try {
			return em.createQuery("SELECT x FROM Documento x ORDER BY x.id DESC", Documento.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			hc.closeConnection();
		}			
    }		
    
}
