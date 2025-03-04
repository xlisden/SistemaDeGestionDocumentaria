package com.unu.sistemadegestiondocumentaria;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.service.EgresadoService;
import com.unu.sistemadegestiondocumentaria.service.EstadoService;
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

        // addGradosInstruccion();
        // addTiposDocumento();
        // addEstados();
        // addPersonas();
        addEgresados();

    }

    private static void addGradosInstruccion() {
        GradoInstruccionService service = new GradoInstruccionService(GradoInstruccion.class);

        // service.delete(1);
        // service.delete(3);
        // service.delete(5);
        // service.delete(6);
        service.add(new GradoInstruccion("Bach."));
        service.add(new GradoInstruccion("Ing."));
        service.add(new GradoInstruccion("Mg."));
        service.add(new GradoInstruccion("MSc."));
        service.add(new GradoInstruccion("Dr."));
        // service.update(3, new GradoInstruccion("updated"));
        // service.update(29, new GradoInstruccion("veinte"));
        // service.delete(10);
        // service.delete(11);
        // service.delete(20);

        imprimirElementos(service.getAll());
    }

    private static void addTiposDocumento() {
        TipoDocumentoService service = new TipoDocumentoService(TipoDocumento.class);

        service.add(new TipoDocumento("OFICIO"));
        service.add(new TipoDocumento("MEMORÁNDUM"));
        service.add(new TipoDocumento("ACTAS DE SUSTENTACIÓN DE TESIS"));
        service.update(5, new TipoDocumento("upda"));
        service.update(2, new TipoDocumento("updated"));
        service.delete(1);
        service.delete(39);

        imprimirElementos(service.getAll());
    }

    private static void addEstados() {
        EstadoService service = new EstadoService(Estado.class);

        service.add(new Estado("PENDIENTE"));
        service.add(new Estado("ENTREGADO"));
        service.delete(2);
        service.update(3, new Estado("updateed1"));
        service.add(new Estado("PENDIENTE"));
        service.add(new Estado("ENTREGADO"));
        service.update(3, new Estado("updateed"));
        service.delete(23);
        service.update(93, new Estado("updateed"));

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

    private static void addEgresados(){
        EgresadoService service = new EgresadoService(Egresado.class);
        PersonaService pService = new PersonaService(Persona.class);
        GradoInstruccionService giService = new GradoInstruccionService(GradoInstruccion.class);

        // service.add(new Egresado(pService.getById(3)));
        // service.add(new Egresado(pService.getById(1)));
        service.update(4, service.getById(2));
        // service.delete(1);
        // service.update(1, new Persona());


        imprimirElementos(service.getAll());
    }

    private static <T> void imprimirElementos(List<T> lista) {
        for (T x : lista) {
            System.out.println(Validation.infoColor + x.toString() + Validation.normalColor);
        }
    }
}
