package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.TipoDocumentoRepository;

public class TipoDocumentoService {

    private static TipoDocumentoService INSTANCIA;

    private TipoDocumentoRepository tdRepository;

    private TipoDocumentoService() {
        tdRepository = TipoDocumentoRepository.instanciar();
        addData();
    }

    public static TipoDocumentoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new TipoDocumentoService();
        }
        return INSTANCIA;
    }

    public void add(TipoDocumento td) {
        tdRepository.add(td);
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
