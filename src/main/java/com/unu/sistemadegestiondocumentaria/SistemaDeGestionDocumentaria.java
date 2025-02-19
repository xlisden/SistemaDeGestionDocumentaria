package com.unu.sistemadegestiondocumentaria;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.IEstadoRepository;
import com.unu.sistemadegestiondocumentaria.repository.IGradoInstruccionRepository;
import com.unu.sistemadegestiondocumentaria.repository.IPersonaRepository;
import com.unu.sistemadegestiondocumentaria.repository.ITipoDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.EstadoRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.GradoInstruccionRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.PersonaRepository;
import com.unu.sistemadegestiondocumentaria.repository.impl.TipoDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {

    public static void main(String[] args) {

        addGradosInstruccion();
        addTiposDocumento();
        addEstados();
        addPersonas();

    }

    private static void addGradosInstruccion() {
        IGradoInstruccionRepository repository = new GradoInstruccionRepository();

        // repository.addGradoInstruccion(new GradoInstruccion("Bach."));
        // repository.addGradoInstruccion(new GradoInstruccion("Ing."));
        // repository.addGradoInstruccion(new GradoInstruccion("Mg."));
        // repository.addGradoInstruccion(new GradoInstruccion("MSc."));
        // repository.addGradoInstruccion(new GradoInstruccion("Dr."));

        imprimirInfo(repository.getAllGradoInstrucciones());
    }

    private static void addTiposDocumento() {
        ITipoDocumentoRepository repository = new TipoDocumentoRepository();

        // repository.addTipoDocumento(new TipoDocumento("OFICIO"));
        // repository.addTipoDocumento(new TipoDocumento("MEMORÁNDUM"));
        // repository.addTipoDocumento(new TipoDocumento("ACTAS DE SUSTENTACIÓN DE TESIS"));

        imprimirInfo(repository.getAllTipoDocumentos());
    }

    private static void addEstados() {
        IEstadoRepository repository = new EstadoRepository();

        // repository.addEstado(new Estado("PENDIENTE"));
        // repository.addEstado(new Estado("ENTREGADO"));

        imprimirInfo(repository.getAllEstados());
    }
    
    private static void addPersonas() {
        IPersonaRepository repository = new PersonaRepository();
        IGradoInstruccionRepository gradoInstruccionRepository = new GradoInstruccionRepository();
        
//        for (int i = 1; i < 5; i++) {
//            repository.addPersona(new Persona("nombre"+i, "apPaterno"+i, "apMaterno"+i, gradoInstruccionRepository.getByIdGradoInstruccion(i)));
//        }
repository.deletePersona(4);

        imprimirInfo(repository.getAllPersonas());
    }    

    private static <T> void imprimirInfo(List<T> lista) {
        for (T x : lista) {
            System.out.println(Validation.infoColor + x.toString() + Validation.normalColor);
        }
    }
}
