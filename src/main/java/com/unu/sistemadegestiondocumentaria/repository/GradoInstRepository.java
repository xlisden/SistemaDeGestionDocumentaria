package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

import javax.persistence.Query;

public class GradoInstRepository extends Repository<GradoInstruccion> {

	private static GradoInstRepository INSTANCIA;

	private GradoInstRepository(Class<GradoInstruccion> type) {
		super(type);
	}
	
	public static GradoInstRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new GradoInstRepository(GradoInstruccion.class);
		}
		return INSTANCIA;
	}

	@Override
	public void update(int id, GradoInstruccion t) {
		GradoInstruccion gi = getById(id);
		if (gi == null) {
			return;
		}

		gi.setNombre(t.getNombre());

		super.update(id, gi);
	}
}
