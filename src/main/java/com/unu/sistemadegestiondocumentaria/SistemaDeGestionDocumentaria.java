package com.unu.sistemadegestiondocumentaria;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.service.GradoInstruccionService;
import com.unu.sistemadegestiondocumentaria.validations.Validation;

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {
    
    public static void main(String[] args) {
        
        addGradosInstruccion();
//        addTiposDocumento();
//        addEstados();
//        addPersonas();

    }
    
    private static void addGradosInstruccion() {
        GradoInstruccionService service = new GradoInstruccionService(GradoInstruccion.class);

        //  service.add(new GradoInstruccion("Bach."));
        //  service.add(new GradoInstruccion("Ing."));
        //  service.add(new GradoInstruccion("Mg."));
        //  service.add(new GradoInstruccion("MSc."));
       service.update(29, new GradoInstruccion("veinte"));
// service.delete(10);
// service.delete(11);
// service.delete(20);

        imprimirInfo(service.getAll());
    }
    
    private static void addTiposDocumento() {
//        ITipoDocumentoRepository repository = new TipoDocumentoRepository();

        // repository.addTipoDocumento(new TipoDocumento("OFICIO"));
        // repository.addTipoDocumento(new TipoDocumento("MEMORÁNDUM"));
        // repository.addTipoDocumento(new TipoDocumento("ACTAS DE SUSTENTACIÓN DE TESIS"));
//        imprimirInfo(repository.getAllTipoDocumentos());
    }
    
    private static void addEstados() {
//        IEstadoRepository repository = new EstadoRepository();

        // repository.addEstado(new Estado("PENDIENTE"));
        // repository.addEstado(new Estado("ENTREGADO"));
//        imprimirInfo(repository.getAllEstados());
    }
    
    private static void addPersonas() {
//        IPersonaRepository repository = new PersonaRepository();
//        IGradoInstruccionRepository gradoInstruccionRepository = new GradoInstruccionService();

//        for (int i = 1; i < 5; i++) {
//            repository.addPersona(new Persona("nombre"+i, "apPaterno"+i, "apMaterno"+i, gradoInstruccionRepository.getByIdGradoInstruccion(i)));
//        }
//        repository.deletePersona(4);
        
//        imprimirInfo(repository.getAllPersonas());
    }    
    
    private static <T> void imprimirInfo(List<T> lista) {
        for (T x : lista) {
            System.out.println(Validation.infoColor + x.toString() + Validation.normalColor);
        }
    }
}
