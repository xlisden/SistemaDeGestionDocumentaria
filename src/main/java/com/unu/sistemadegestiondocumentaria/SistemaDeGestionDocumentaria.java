package com.unu.sistemadegestiondocumentaria;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.service.AdministrativoService;
import com.unu.sistemadegestiondocumentaria.service.DetDestinatarioService;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.EgresadoService;
import com.unu.sistemadegestiondocumentaria.service.EstadoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import com.unu.sistemadegestiondocumentaria.service.GradoInstruccionService;
import com.unu.sistemadegestiondocumentaria.service.OficioService;
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
        addTiposDocumento();
        addEstados();
        addAdministrativos();
        addExpedientes();
        // addOficios();

        // addPersonas();
        // addEgresados();
        // addDocumentos();
        // testDetalles();

        // System.out.println(Validation.magentaColor + "testDetDestinatarios()" + Validation.normalColor);
        // testDetalles();
        // System.out.println(Validation.magentaColor + "finalizo()" +Validation.normalColor);
    }

    private static void addGradosInstruccion() {
        GradoInstruccionService service = GradoInstruccionService.instanciar();

        // service.delete(3);
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

        imprimirElementos(service.getAll());
    }

    private static void addTiposDocumento() {
        TipoDocumentoService service = TipoDocumentoService.instanciar();
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
        EstadoService service = EstadoService.instanciar();

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
        PersonaService service = PersonaService.instanciar();

        for (int i = 1; i <= 5; i++) {
            service.add(new Persona("nombre" + i, "apPaterno" + i, "apMaterno" + i, i));
        }
        //  service.delete(4);
        //  service.delete(39);
        //  //sin ap p
        //  service.update(3, new Persona("nombre", "", "apellidoMaterno",4));        
        //  service.update(1, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno",3));
        // // // sin persona
        //  service.update(32, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno", 3));
        // // //gi vacio
        //  service.update(1, new Persona("uunombre", "uuuuu", "uuuuuapellidoMaterno", 0));

        imprimirElementos(service.getAll());
    }

    private static void addEgresados() {
        EgresadoService service = EgresadoService.instanciar();

        //  for (int i = 1; i <= 4; i++) {
        //  service.add(new Persona("*nombre_"+i, "*apPat_"+i, "*apMat_"+i, i));
        //  }
        // service.delete(3);
        // service.update(1, new Persona("hola", "soy", "nuevo", 2));
        // service.delete(30);
        // service.update(22, new Persona());
        // service.update(2, new Persona("", "soy", "nuevo", 2));
        
        imprimirElementos(service.getAll());
    }

    private static void addAdministrativos() {
        AdministrativoService service = AdministrativoService.instanciar();

        if (service.getAll().isEmpty()) {
            for (int i = 1; i <= 6; i++) {
                int idGradoInst = (int) (Math.random() * 5) + 1;
                service.add(new Persona("*name*ad*" + i, "*appat*ad*" + i, "*apmat*ad*" + i, idGradoInst));
            }
        }

        //  service.update(3, new Persona("hola", "soy una", "nueva persona", 1));
        //  service.add(new Persona("we**", "are**", "banditos**", 9));
        //  service.update(1, new Persona("hola", "", "nuevo", 2));
        //  service.delete(5);
        //  service.delete(52);

         imprimirElementos(service.getAll());
    }

    private static void addExpedientes() {
        ExpedienteService service = ExpedienteService.instanciar();

        if (service.getAll().isEmpty()) {
            for (int i = 1; i <= 6; i++) {
                int idGradoInst = (int) (Math.random() * 5) + 1;
                service.add(new Persona("exp*nom" + i, "exp*apPat" + i, "exp*apMat" + i, idGradoInst));
            }
        }

        service.update(1, new Persona("hola", "soy", "nuevo", 2));
        service.delete(6);
        service.delete(7); 
        service.delete(38); 
        service.add(new Persona("soy8", "el8", "nro8", 2));
       service.add(new Persona("hola, yo", "ser nueva", "persona", 9));
       service.update(39, new Persona());
       imprimirElementos(service.getAll());
    }

    private static void addDocumentos() {
        DocumentoService service = DocumentoService.instanciar();

        // service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 2));
        // service.add(new Documento(Date.valueOf(LocalDate.now()), 3, 2));
        // service.update(4, new Documento(Date.valueOf("2024-04-10"), 3, 1));
        // service.delete(7);
        // service.delete(129);
        // service.getById(42);
        // service.updateEstadoDocumento(2);
        imprimirElementos(service.getAll());
    }

    private static void addOficios() {
        OficioService service = OficioService.instanciar();

        // service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 2));
        // service.add(new Documento(Date.valueOf(LocalDate.now()), 3, 2));
        if (service.getAll().isEmpty()) {
            for (int i = 1; i <= 6; i++) {
                int idTipoDoc = (int) (Math.random() * 3) + 1;
                int idEmisor = (int) (Math.random() * 6) + 1;
                int idExp = (int) (Math.random() * 6) + 1;
                List<Integer> dest = new ArrayList<>();
                dest.add(4);
                dest.add(2);

                service.add(new Oficio(Date.valueOf(LocalDate.now()), idTipoDoc, idEmisor, dest, idExp, "asunto" + i, "referencia" + i));
            }
        }

        // service.update(4, new Oficio(Date.valueOf("2024-04-10"), 1, 1, "asunto**",
        // "referencia4"));
        // service.update(2, new Oficio(Date.valueOf("2022-02-12"), 2,2, "**asunto",
        // "**referencia"));
        // service.delete(7);
        // service.delete(129);
        service.delete(3);
        // service.updateEstadoDocumento(2);
        // imprimirElementos(service.getAll());
    }

    private static void testDetalles() {
        DetDestinatarioService service = DetDestinatarioService.instanciar();
        // AdministrativoService adService = AdministrativoService.instanciar();
        DocumentoService docService = DocumentoService.instanciar();

        // List<Integer> dest = new ArrayList<>();
        // dest.add(4);
        // int idDest = 1;
        // docService.updateDestinatario(2, 2, 1);
        // docService.updateDestinatario(3, 2, 3);
        // docService.updateDestinatario(4, 2, 5);
        // docService.deleteDestinatario(6, 3);
        // docService.deleteDocDependencias(3);
        docService.updateExpediente(5, 5, 1);
        docService.updateExpediente(1, 6, 9);
    }

    private static <T> void imprimirElementos(List<T> lista) {
        for (T x : lista) {
            System.out.println(Validation.infoColor + x.toString() + Validation.normalColor);
        }
    }

}
