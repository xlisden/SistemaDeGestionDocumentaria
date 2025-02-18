package com.unu.sistemadegestiondocumentaria;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.IGradoInstruccionRepository;
import com.unu.sistemadegestiondocumentaria.repository.ITipoDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.GradoInstruccionRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.TipoDocumentoRepository;

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {

    public static void main(String[] args) {
        IGradoInstruccionRepository gradoInstruccionRepository = new GradoInstruccionRepository();
        ITipoDocumentoRepository tipoDocumentoRepository = new TipoDocumentoRepository();
        
        gradoInstruccionRepository.addGradoInstruccion(new GradoInstruccion(""));
        gradoInstruccionRepository.getByIdGradoInstruccion(3);
        
        tipoDocumentoRepository.addTipoDocumento(new TipoDocumento(""));
        tipoDocumentoRepository.getByIdTipoDocumento(4);
    }
}
