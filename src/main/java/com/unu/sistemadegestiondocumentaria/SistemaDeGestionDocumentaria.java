package com.unu.sistemadegestiondocumentaria;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleSustentacion;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.Sustentacion;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.service.AdministrativoService;
import com.unu.sistemadegestiondocumentaria.service.DetDestinatarioService;
import com.unu.sistemadegestiondocumentaria.service.DetSustentacionService;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.EgresadoService;
import com.unu.sistemadegestiondocumentaria.service.EstadoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import com.unu.sistemadegestiondocumentaria.service.GradoInstruccionService;
import com.unu.sistemadegestiondocumentaria.service.OficioService;
import com.unu.sistemadegestiondocumentaria.service.PersonaService;
import com.unu.sistemadegestiondocumentaria.service.SustentacionService;
import com.unu.sistemadegestiondocumentaria.service.TipoDocumentoService;
import com.unu.sistemadegestiondocumentaria.validations.Validation;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.popup.component.SimplePopupBorder;

/**
 *
 * @author Daye
 */
public class SistemaDeGestionDocumentaria {

	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		addGradosInstruccion();
		
		long start = System.currentTimeMillis();

		addAdministrativos();

		long end = System.currentTimeMillis();
		System.out.println("\ntiempo = " + (end - start) + " ms");

//		System.out.println(Validation.showInMagenta("\nhola"));
//		System.out.println(Validation.showInMagenta("\nfinalizo()"));
	}

	private static void addAdministrativos() {
		AdministrativoService service = AdministrativoService.instanciar();
//		imprimirElementos(service.getAll());
	}

	private static void addPersonas() {
		PersonaService.instanciar();
	}

	private static void addEstados() {
		EstadoService service = EstadoService.instanciar();
		try {
			System.out.println("est = " + service.getByNombre("PENDIENTE"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addTiposDocumento() {
		TipoDocumentoService.instanciar();
	}

	private static void addGradosInstruccion() {
		GradoInstruccionService.instanciar();
	}

	private static void pruebaConValidate() {
		try {

			EgresadoService service = EgresadoService.instanciar();

			Persona persona = new Persona("nom1", "appat1", "appmat", 2);
			Validation.validatePersona(persona);

			service.add(persona);
			imprimirElementos(service.getAll());
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

	private static void addEgresados() {
		EgresadoService service = EgresadoService.instanciar();

		// for (int i = 1; i <= 4; i++) {
		// service.add(new Persona("*nombre_"+i, "*apPat_"+i, "*apMat_"+i, i));
		// }
		// service.delete(3);
		// service.update(1, new Persona("hola", "soy", "nuevo", 2));
		// service.delete(30);
		// service.update(22, new Persona());
		// service.update(2, new Persona("", "soy", "nuevo", 2));
//        imprimirElementos(service.getAll());
	}

	private static void addExpedientes() {
		ExpedienteService service = ExpedienteService.instanciar();

		if (service.getAll().isEmpty()) {
			for (int i = 1; i <= 6; i++) {
				int idGradoInst = (int) (Math.random() * 5) + 1;
				service.add(new Persona("exp*nom" + i, "exp*apPat" + i, "exp*apMat" + i, 1));
			}
		}
//
//        service.update(1, new Persona("hola", "soy", "nuevo", 2));
//        service.delete(6);
//        service.delete(7); 
//        service.delete(38); 
//        service.add(new Persona("soy8", "el8", "nro8", 2));
		service.add(new Persona("hola, yo", "ser nueva", "persona", 9));
		service.add(new Persona("hola, yo", "", "persona", 3));
//       service.update(39, new Persona());

//       imprimirElementos(service.getAll());
	}

	private static void addDocumentos() {
		DocumentoService service = DocumentoService.instanciar();

		List<Integer> dest1 = new ArrayList<>();
		List<Integer> dest2 = new ArrayList<>();
		dest1.add(3);
		dest1.add(49);
		dest1.add(6);
		// un emisor correcto, uno no

//         service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest2, 2));
//         service.add(new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest1, 2));
//         service.update(4, new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest1, 9));
//          service.update(4, new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest1, 3));
		// expediente no existente
//         service.update(1, new Documento(Date.valueOf(LocalDate.now()), 1, 2, dest2, 9));
//         service.update(1, new Documento(Date.valueOf("2024-02-20"), 1, 2, dest2, 1)); // si es nulo, no hay necesidad de actualizar, por eso no hay advertencia
//         service.update(1, new Documento(Date.valueOf("2024-02-20"), 1, 2, dest1, 1));
//         service.update(3, new Documento(Date.valueOf("2023-03-03"), 1, 2, dest2, 9));
//         service.delete(7);
//         service.delete(129);
//         service.getById(2);
//         service.updateEstadoDocumento(2);
//         service.updateEstadoDocumento(1);
		imprimirElementos(service.getAll());
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

				service.add(new Oficio(Date.valueOf(LocalDate.now()), idTipoDoc, idEmisor, dest, idExp, "asunto" + i,
						"referencia" + i));
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
