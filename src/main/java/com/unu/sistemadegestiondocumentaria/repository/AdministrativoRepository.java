package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;

public class AdministrativoRepository extends Repository<Administrativo> {

	private static AdministrativoRepository INSTANCIA;

	private AdministrativoRepository(Class<Administrativo> type) {
		super(type);
	}

	public static AdministrativoRepository instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new AdministrativoRepository(Administrativo.class);
		}
		return INSTANCIA;
	}
	
}
