package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;

public class TipoDocRepository extends Repository<TipoDocumento> {

	private static TipoDocRepository INSTANCIA;

	private TipoDocRepository(Class<TipoDocumento> type) {
		super(type);
	}
	
	public static TipoDocRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new TipoDocRepository(TipoDocumento.class);
		}
		return INSTANCIA;
	}

}
