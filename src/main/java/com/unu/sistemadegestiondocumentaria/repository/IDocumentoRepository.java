package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import java.util.List;

public interface IDocumentoRepository {

    public void addDocumento(Documento documento);

    public void updateDocumento(int id, Documento documento);

    public void deleteDocumento(int id);

    public List<Documento> getAllDocumentos();

    public Documento getByIdDocumento(int id);
    
    public void updateEstadoDocumento(int id);
    
}
