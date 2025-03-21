package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Sustentacion;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class SustentacionService extends Repository<Sustentacion> {

	private static SustentacionService INSTANCIA;

	private final AdministrativoService adService = AdministrativoService.instanciar();

	private SustentacionService(Class<Sustentacion> type) {
		super(type);
	}

	public static SustentacionService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new SustentacionService(Sustentacion.class);
		}
		return INSTANCIA;
	}

	public void add(Sustentacion s) {
		try {
			s = getSustentacion(s, 0);

			super.add(s);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}

	@Override
	public void update(int id, Sustentacion t) {
		try {
			Sustentacion s = getById(id);
			if (s == null) {
				return;
			}

			s = getSustentacion(t, s.getId());

			super.update(id, s);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}

	@Override
	public void delete(int id) {
		try {
			super.delete(id);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}

	@Override
	public Sustentacion getById(int id) {
		try {
			return super.getById(id);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
		return null;
	}

	private Administrativo getAdministrativo(int id) {
		return (id != 0) ? adService.getById(id) : null;
	}

	private Sustentacion getSustentacion(Sustentacion s, int idSust) {
		Administrativo ad = null;
		
		ad = getAdministrativo(s.getIdAsesor());
		if (ad != null) {
			s.setAsesor(ad);
		}
		
		ad = getAdministrativo(s.getIdJurado1());
		if (ad != null) {
			s.setJurado1(ad);
		}
		
		ad = getAdministrativo(s.getIdJurado2());
		if (ad != null) {
			s.setJurado2(ad);
		}
		
		ad = getAdministrativo(s.getIdJurado3());
		if (ad != null) {
			s.setJurado3(ad);
		}

		if (idSust != 0) {
			s.setId(idSust);
		}

		return s;
	}
}
