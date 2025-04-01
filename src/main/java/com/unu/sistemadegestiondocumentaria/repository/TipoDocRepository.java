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

	@Override
	public void update(int id, TipoDocumento t) {
		TipoDocumento gi = getById(id);
		if (gi == null) {
			return;
		}

		gi.setNombre(t.getNombre());

		super.update(id, gi);
	}
}
