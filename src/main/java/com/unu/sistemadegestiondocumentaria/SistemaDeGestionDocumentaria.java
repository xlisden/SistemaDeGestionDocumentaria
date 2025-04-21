package com.unu.sistemadegestiondocumentaria;

//<editor-fold defaultstate="collapsed" desc=" Librerías...">
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.unu.sistemadegestiondocumentaria.entity.DetalleSustentacion;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Sustentacion;
import com.unu.sistemadegestiondocumentaria.service.DetDestinatarioService;
import com.unu.sistemadegestiondocumentaria.service.DetSustentacionService;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.OficioService;
import com.unu.sistemadegestiondocumentaria.service.SustentacionService;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import java.util.Arrays;
import java.util.stream.Collectors;
//</editor-fold>

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
//		addGradosInstruccion();

        long start = System.currentTimeMillis();
        addDocumentos();
        long end = System.currentTimeMillis();
        System.out.println("\ntiempo = " + (end - start) + " ms");

//		System.out.println(Validation.showInMagenta("\nhola"));
//		System.out.println(Validation.showInMagenta("\nfinalizo()"));
    }

    private static void hola() {
        Date fecha = Date.valueOf("2024-11-23");
        Date fecha1 = Date.valueOf("2024-10-23");
        Date fecha2 = Date.valueOf("2024-12-23");
        
        if(fecha2.compareTo(fecha1) >= 0){
            System.out.println(">= 0");
        }
        if(fecha1.compareTo(fecha) <= 0){
            System.out.println("<= 0");
        }
        
        if(fecha1.compareTo(fecha) <= 0 && fecha2.compareTo(fecha) >= 0){
            System.out.println("hola, esta dentro");
        }
        
//        /*
//docs.stream().filter(x -> x.equals(x.getIdExpedientes().contains(idExp))).collect(Collectors.toList());        
//         */
//        List<Integer> lista = Arrays.asList(2, 3, 2, 22, 34, 2, 31, 9, 3);
//
//        lista = lista.stream().filter(x -> x.equals(2)).collect(Collectors.toList());
//
//        System.out.println(lista);
    }

    private static void addDocumentos() {
        try {
            DocumentoService service = DocumentoService.instanciar();
//            imprimirElementos(service.getAll());
            System.out.println("--------------------------------------------------------");
            System.out.println(service.getIdByNombre("MEMORÁNDUM 004"));
            System.out.println(service.getIdByNombre("MEMORÁNDUM 003"));

//            List<Integer> dest = new ArrayList<>();
//            dest.add(3);
//            dest.add(12);
//            List<Integer> exp = new ArrayList<>();
//            exp.add(19);
//            exp.add(20);

//            service.add(new Documento(Date.valueOf(LocalDate.now()), 2, 3, dest, exp));
//            dest.add(4);
//            service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest, exp));
//            exp = new ArrayList<>();
//            exp.add(23);
//            service.add(new Documento(Date.valueOf(LocalDate.now()), 2, 9, dest, exp));
//            dest.add(11);
//            service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 8, dest, exp));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void pruebaConValidate() {
        try {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
        }

    }

    private static void addDetSust() {
        try {
            DetSustentacionService service = DetSustentacionService.instanciar();
//			SustentacionService sustService = SustentacionService.instanciar();
            Sustentacion sust = new Sustentacion();

            // no existe el exp
            sust = new Sustentacion();
            sust.setIdAsesor(1);
            sust.setIdJurado1(2);
            sust.setIdJurado2(3);
            sust.setIdJurado3(4);
            service.addDet(22, sust);

            // no existe el sust
            sust = new Sustentacion();
            sust.setIdAsesor(1);
            sust.setIdJurado1(2);
            sust.setIdJurado2(3);
            sust.setIdJurado3(4);
            service.add(new DetalleSustentacion(2, 88));

            // sin jurados el sust - no falla
            sust = new Sustentacion();
            sust.setIdAsesor(3);
            service.addDet(3, sust);

            // sust vacio - no falla
            sust = new Sustentacion();
            service.addDet(4, sust);

            // todo correcto
            sust = new Sustentacion();
            sust.setIdAsesor(5);
            sust.setIdJurado1(4);
            sust.setIdJurado2(3);
            sust.setIdJurado3(2);
            service.addDet(5, sust);

            imprimirElementos(service.getAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void addSust() {
        try {
            SustentacionService service = SustentacionService.instanciar();
            Sustentacion sust = new Sustentacion();

//			// solo asesor, no falla
//			sust = new Sustentacion();
//			sust.setIdAsesor(3);
//			service.add(sust);
//			// no falla
//			service.add(new Sustentacion());
//			// no jurado1 (no existe)
//			sust = new Sustentacion();
//			sust.setIdAsesor(6);
//			sust.setIdJurado1(99);
//			sust.setIdJurado2(5);
//			sust.setIdJurado3(4);
//			service.add(sust);
//			// correcto
//			service.delete(1);
//			// no falla
//			service.delete(4);
//			// correcto
//			sust = new Sustentacion();
//			sust.setIdAsesor(1);
//			sust.setIdJurado1(2);
//			sust.setIdJurado2(3);
//			sust.setIdJurado3(4);
//			service.update(7, sust);
//			// no jurado 2 && no existe
//			sust = new Sustentacion();
//			sust.setIdAsesor(2);
//			sust.setIdJurado1(1);
//			sust.setIdJurado2(77);
//			sust.setIdJurado3(3);
//			service.update(44, sust);
            imprimirElementos(service.getAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void addOficios() {
        OficioService service = OficioService.instanciar();

        List<Integer> vacio = new ArrayList<>();
        List<Integer> dest1 = new ArrayList<>();
        dest1.add(4);
        dest1.add(6);

        // sin exp
//         service.add(new Oficio(Date.valueOf(LocalDate.now()), 1, 1, dest1, 0, "asunto", "referencia"));
        // error
        // todo correcto
        if (service.getAll().isEmpty()) {
            for (int i = 1; i <= 6; i++) {
                int idTipoDoc = (int) (Math.random() * 3) + 1;
                int idEmisor = (int) (Math.random() * 6) + 1;
                int idExp = (int) (Math.random() * 6) + 1;
                List<Integer> dest = new ArrayList<>();
                dest.add(4);
                dest.add(2);

//                service.add(new Oficio(Date.valueOf(LocalDate.now()), idTipoDoc, idEmisor, dest, idExp, "asunto" + i,
//                        "referencia" + i));
            }
        }

        // todo bien
//         service.update(4, new Oficio(Date.valueOf("2024-04-10"), 1, 1, dest1, 3, "asunto**", "referencia4"));
        // sin referencia
//         service.update(4, new Oficio(Date.valueOf("2022-02-12"), 2,2,dest1, 2, "**asunto", ""));
        // no existe 7
//         service.delete(7);
        // no existe 9
//         service.delete(129);
        // existe 5
//         service.delete(5);
        // existe
//        service.updateEstadoDocumento(4);
        // no existe
//         service.updateEstadoDocumento(9);
//         // sin dest
//         service.add(new Oficio(Date.valueOf(LocalDate.now()), 1, 1, vacio, 1, "asunto", "referencia"));
//         // sin tipodoc ()
//         service.add(new Oficio(Date.valueOf(LocalDate.now()), 8, 1, dest1, 1, "asunto", "referencia"));
//         // sin fecha
//         service.add(new Oficio(null, 3, 1, dest1, 1, "asunto", "referencia"));
        imprimirElementos(service.getAll());
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

    private static <T> void imprimirElemento(T t) {
        System.out.println(Validation.infoColor + t.toString() + Validation.normalColor);
    }

}

/*
    TESTEADOS
    * AdministrativoService
    * EstadoService
    * ExpedienteService
    * GradoInstruccionService
    * PersonaService
    * TipoDocumentoService
 */
