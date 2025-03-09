package com.unu.sistemadegestiondocumentaria;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.service.AdministrativoService;
import com.unu.sistemadegestiondocumentaria.service.EgresadoService;
import com.unu.sistemadegestiondocumentaria.service.EstadoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import com.unu.sistemadegestiondocumentaria.service.GradoInstruccionService;
import com.unu.sistemadegestiondocumentaria.service.PersonaService;
import com.unu.sistemadegestiondocumentaria.service.TipoDocumentoService;
import com.unu.sistemadegestiondocumentaria.validations.Validation;

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {

    public static void main(String[] args) {

        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        addGradosInstruccion();
        // addTiposDocumento();
        // addEstados();
        // addPersonas();
        // addEgresados();
        // addExpedientes();

        System.out.println(Validation.infoColor + "addExpedientes()" + Validation.normalColor);
        addExpedientes();
        System.out.println(Validation.infoColor + "finalizo()" + Validation.normalColor);

//        System.out.println(Validation.infoColor + "addEgresados()" + Validation.normalColor);
//        addEgresados();
//        System.out.println(Validation.infoColor + "finalizo()" + Validation.normalColor);
    }

    private static void addGradosInstruccion() {
        GradoInstruccionService service = new GradoInstruccionService(GradoInstruccion.class);

        // service.delete(1);
        // service.delete(3);
        // service.delete(5);
        // service.delete(6);
        if (service.getAll().isEmpty()) {
            service.add(new GradoInstruccion("Bach."));
            service.add(new GradoInstruccion("Ing."));
            service.add(new GradoInstruccion("Mg."));
            service.add(new GradoInstruccion("MSc."));
            service.add(new GradoInstruccion("Dr."));
        }
        // service.update(3, new GradoInstruccion("updated"));
        // service.update(29, new GradoInstruccion("veinte"));
        // service.delete(10);
        // service.delete(11);
        // service.delete(20);

        // imprimirElementos(service.getAll());
    }

    private static void addTiposDocumento() {
        TipoDocumentoService service = new TipoDocumentoService(TipoDocumento.class);
        if (service.getAll().isEmpty()) {
            service.add(new TipoDocumento("OFICIO"));
            service.add(new TipoDocumento("MEMORÁNDUM"));
            service.add(new TipoDocumento("ACTAS DE SUSTENTACIÓN DE TESIS"));
        }
        // service.update(5, new TipoDocumento("upda"));
        // service.update(2, new TipoDocumento("updated"));
        // service.delete(1);
        // service.delete(39);

        imprimirElementos(service.getAll());
    }

    private static void addEstados() {
        EstadoService service = new EstadoService(Estado.class);

        if (service.getAll().isEmpty()) {
            service.add(new Estado("PENDIENTE"));
            service.add(new Estado("ENTREGADO"));
        }
        // service.delete(2);
        // service.update(3, new Estado("updateed1"));
        // service.add(new Estado("PENDIENTE"));
        // service.add(new Estado("ENTREGADO"));
        // service.update(3, new Estado("updateed"));
        // service.delete(23);
        // service.update(93, new Estado("updateed"));

        imprimirElementos(service.getAll());
    }

    private static void addPersonas() {
        PersonaService service = new PersonaService(Persona.class);
        GradoInstruccionService giService = new GradoInstruccionService(GradoInstruccion.class);

        for (int i = 1; i <= 5; i++) {
            service.add(new Persona("nombre" + i, "apPaterno" + i, "apMaterno" + i, giService.getById(i)));
        }
        // service.delete(4);
        // service.delete(39);
        // //sin ap p
        // service.update(3, new Persona("nombre", "", "apellidoMaterno", giService.getById(4)));
        // //sin gi
        // service.update(1, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno", giService.getById(3)));
        // // sin persona
        // service.update(32, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno", giService.getById(3)));
        // //gi vacio
        // service.update(1, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno", new GradoInstruccion()));

        imprimirElementos(service.getAllPersonas());
    }

    private static void addEgresados() {
        EgresadoService service = new EgresadoService(Egresado.class);
        // GradoInstruccionService giService = new GradoInstruccionService(GradoInstruccion.class);

//         for (int i = 1; i <= 4; i++) {
//             service.add(new Persona("*nombre_"+i, "*apPat_"+i, "*apMat_"+i, i));
//         }
        service.delete(3);
        service.update(1, new Persona("hola", "soy", "nuevo", 2));
        imprimirElementos(service.getAll());
    }

    private static void addAdministrativos() {
        AdministrativoService service = new AdministrativoService(Administrativo.class);

//        for (int i = 1; i <= 4; i++) {
//            service.add(new Persona("*name*" + i, "*appat*" + i, "*apmat*" + i, i));
//        }
//        service.update(3, new Persona("hola", "soy una", "nueva persona", 1));
        service.add(new Persona("we**", "are**", "banditos**", 2));
//        service.update(1, new Persona("hola", "soy", "nuevo", 2));
        service.delete(5);

        imprimirElementos(service.getAll());
    }

    private static void addExpedientes() {
        ExpedienteService service = new ExpedienteService(Expediente.class);

//        for (int i = 1; i <= 4; i++) {
//            service.add(new Persona("**nom"+i, "**apPat"+i, "**apMat"+i, i));
//        }
//        service.update(1, new Persona("hola", "soy", "nuevo", 2));
        service.delete(6);
        service.delete(7);
        service.add(new Persona("soy8", "el8", "nro8", 2));
        imprimirElementos(service.getAll());
    }

    private static <T> void imprimirElementos(List<T> lista) {
        for (T x : lista) {
            System.out.println(Validation.infoColor + x.toString() + Validation.normalColor);
        }
    }
}
