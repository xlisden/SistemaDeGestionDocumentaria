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
			Administrativo asesor = null;
			Administrativo jurado1 = null;
			Administrativo jurado2 = null;
			Administrativo jurado3 = null;

			asesor = obtenerAdministrativo(s.getIdAsesor());
			jurado1 = obtenerAdministrativo(s.getIdJurado1());
			jurado2 = obtenerAdministrativo(s.getIdJurado2());
			jurado3 = obtenerAdministrativo(s.getIdJurado3());

//			if (asesor == null || jurado1 == null || jurado2 == null || jurado3 == null) {
//				return;
//			}

			s.setAsesor(asesor);
			s.setJurado1(jurado1);
			s.setJurado2(jurado2);
			s.setJurado3(jurado3);

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

//			Administrativo asesor = adService.getById(s.getIdAsesor());
//			Administrativo jurado1 = adService.getById(s.getIdJurado1());
//			Administrativo jurado3 = adService.getById(s.getIdJurado3());
//			Administrativo jurado2 = adService.getById(s.getIdJurado2());
			
	        Administrativo asesor = obtenerAdministrativo(t.getIdAsesor());
	        Administrativo jurado1 = obtenerAdministrativo(t.getIdJurado1());
	        Administrativo jurado2 = obtenerAdministrativo(t.getIdJurado2());
	        Administrativo jurado3 = obtenerAdministrativo(t.getIdJurado3());
			System.out.println("*********");
			if (asesor == null || jurado1 == null || jurado2 == null || jurado3 == null) {
				System.out.println("en el null");
				return;
			}
			
			s.setAsesor(t.getAsesor());
			s.setJurado1(t.getJurado1());
			s.setJurado2(t.getJurado2());
			s.setJurado3(t.getJurado3());

			super.update(id, t);
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

	private Administrativo obtenerAdministrativo(int id) {
		System.out.println("id = " + id) ;
		return (id != 0) ? adService.getById(id) : null;
	}
}
