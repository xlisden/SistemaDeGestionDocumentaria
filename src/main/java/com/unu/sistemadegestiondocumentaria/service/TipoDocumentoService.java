package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.repository.TipoDocRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class TipoDocumentoService {

	private static TipoDocumentoService INSTANCIA;

	private TipoDocRepository tdRepository;

	private TipoDocumentoService() {
		tdRepository = TipoDocRepository.instanciar();
		addData();
	}

	public static TipoDocumentoService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new TipoDocumentoService();
		}
		return INSTANCIA;
	}

	public void add(TipoDocumento t) {
		tdRepository.add(t);
	}

	public List<TipoDocumento> getAll() {
		return tdRepository.getAll();
	}
	
	public TipoDocumento getById(int id) {
		return tdRepository.getById(id);
	}

	private void addData() {
		if (getAll().isEmpty()) {
			add(new TipoDocumento("OFICIO"));
			add(new TipoDocumento("MEMORÁNDUM"));
			add(new TipoDocumento("ACTAS DE SUSTENTACIÓN DE TESIS"));
		}
	}
}
