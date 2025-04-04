package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;

public class TipoDocumentoRepository extends Repository<TipoDocumento> {

	private static TipoDocumentoRepository INSTANCIA;

	private TipoDocumentoRepository(Class<TipoDocumento> type) {
		super(type);
	}
	
	public static TipoDocumentoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new TipoDocumentoRepository(TipoDocumento.class);
		}
		return INSTANCIA;
	}

}
