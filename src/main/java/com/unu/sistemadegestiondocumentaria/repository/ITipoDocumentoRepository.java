package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import java.util.List;

public interface ITipoDocumentoRepository {

    public void addTipoDocumento(TipoDocumento tipoDocumento);

    public void updateTipoDocumento(int id, TipoDocumento tipoDocumento);

    public void deleteTipoDocumento(int id);

    public List<TipoDocumento> getAllTipoDocumentos();

    public TipoDocumento getByIdTipoDocumento(int id);
}
