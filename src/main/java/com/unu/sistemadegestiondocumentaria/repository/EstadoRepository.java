package com.unu.sistemadegestiondocumentaria.repository;

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
	
	@Override
	public void update(int id, Estado t) {
		Estado gi = getById(id);
		if (gi == null) {
			return;
		}

		gi.setNombre(t.getNombre());

		super.update(id, gi);
	}
}
