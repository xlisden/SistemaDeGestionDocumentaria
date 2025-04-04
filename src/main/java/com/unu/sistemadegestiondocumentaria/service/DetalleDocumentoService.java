package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDocumentoRepository;
import java.util.List;

public class DetalleDocumentoService {

	private static DetalleDocumentoService INSTANCIA;

	private DetalleDocumentoRepository detDocRepository;

	private DetalleDocumentoService() {
		detDocRepository = DetalleDocumentoRepository.instanciar();
	}

	public static DetalleDocumentoService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new DetalleDocumentoService();
		}
		return INSTANCIA;
	}

	// esto no deberia de ir, ya que al final docService llamara a add de Repository
	// a menos que se agregue desde la tabla de destinatarios
	public void add(DetalleDocumento t) {
		detDocRepository.add(t);
	}

	// no hacemos update verificando si hay mas o menos expedientes, porque solo son dos
	// entonces se eliminan los restantes si se separan los bachilleres
	public void updateExp(DetalleDocumento t, Expediente exp) {
		int id = getId(t.getDocumento().getId(), t.getExpediente().getId());
		DetalleDocumento detExp = getById(id);
		// el id se trae de la bd, claro que existe (mejor no, por si acaso)
		if (detExp == null) {
			return;
		}

		detExp.setExpediente(exp); // se supone que docService valido que exp existe
		detDocRepository.update(id, detExp);
	}

	public void delete(int idDoc, int idExp) {
		int id = getId(idDoc, idExp);
		detDocRepository.delete(id);
	}

	public DetalleDocumento getById(int id) {
		return detDocRepository.getById(id);
	}

	public int getId(int idDoc, int idExp) {
		return detDocRepository.getId(idDoc, idExp);
	}

	public void deleteByDoc(int idDoc) {
		detDocRepository.deleteByDoc(idDoc);
	}

	public void deleteByExp(int idExp) {
		detDocRepository.deleteByExp(idExp);
	}

	public List<Expediente> getExpedientesByDoc(int idDoc) {
		return detDocRepository.getExpedientesByDoc(idDoc);
	}

	public List<Documento> getDocsByExp(int idExp) {
		return detDocRepository.getDocsByExp(idExp);
	}

}
