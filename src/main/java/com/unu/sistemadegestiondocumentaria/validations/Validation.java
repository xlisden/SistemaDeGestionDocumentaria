package com.unu.sistemadegestiondocumentaria.validations;

import com.unu.sistemadegestiondocumentaria.entity.ActaSustentacionTesis;
import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.DetalleSustentacion;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Memorandum;
import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;

public class Validation {

    public static String warningColor = "\033[43m";
    public static String normalColor = "\033[0m";
    public static String infoColor = "\033[34m";
    public static String magentaColor = "\033[35m";

    public static void validateGradoInstruccion(GradoInstruccion gi) {
        if (gi.getNombre() == null || gi.getNombre().isBlank()) {
            throw new ValidationException("El nombre del Grado de Instrucción no puede estar vacío.");
        }
    }

    public static void validateTipoDocumento(TipoDocumento td) {
        if (td.getNombre() == null || td.getNombre().isBlank()) {
            throw new ValidationException("El nombre del Tipo de Documento no puede estar vacío.");
        }
    }

    public static void validateEstado(Estado e) {
        if (e.getNombre() == null || e.getNombre().isBlank()) {
            throw new ValidationException("El nombre del Estado no puede estar vacío.");
        }
    }

    public static void validatePersona(Persona p) {
        if (p == null) {
            throw new ValidationException("La Persona no puede estar vacía.");
        } // p.getNombre() == null ||

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new ValidationException("El nombre de la Persona no puede estar vacío.");
        }

        if (p.getApellidoPaterno() == null || p.getApellidoPaterno().isBlank()) {
            throw new ValidationException("El apellido paterno de la Persona no puede estar vacío.");
        }

        if (p.getApellidoMaterno() == null || p.getApellidoMaterno().isBlank()) {
            throw new ValidationException("El apellido materno de la Persona no puede estar vacío.");
        }

//        if (p.getGradoInstruccion() == null) {
//            throw new ValidationException("El Grado de Instrucción de la Persona no puede estar vacío.");
//        }
//        validateGradoInstruccion(p.getGradoInstruccion());
    }

//    public static void validateEgresado(Egresado e) {
//        if (e.getPersona() == null) {
//            throw new ValidationException("La Persona del Egresado no puede estar vacía.");
//        }
//        validatePersona(e.getPersona());
//    }

    public static void validateAdministrativo(Administrativo ad) {
        if (ad.getPersona() == null) {
            throw new ValidationException("La Persona del Administrativo no puede estar vacía.");
        }
        validatePersona(ad.getPersona());
    }

    public static void validateExpediente(Expediente exp) {
        if (exp.getPersona() == null) {
            throw new ValidationException("La Persona del Expediente no puede estar vacío");
        }
        validatePersona(exp.getPersona());
    }

    public static void validateDocumento(Documento doc) {
        if (doc.getFechaEmision() == null || doc.getFechaEmision().toString().isBlank()) {
            throw new ValidationException("La fecha de emisión del Documento no puede estar vacía.");
        }

        if (doc.getTipoDocumento() == null) {
            throw new ValidationException("El tipo de documento del Documento no puede estar vacío.");
        }
        validateTipoDocumento(doc.getTipoDocumento());

        if (doc.getEstado() == null) {
            throw new ValidationException("El estado del Documento no puede estar vacío.");
        }
        validateEstado(doc.getEstado());

        if (doc.getEmisor() == null) {
            throw new ValidationException("El emisor del Documento no puede estar vacío.");
        }
        validateAdministrativo(doc.getEmisor());

        if (doc.getIdExpediente() == 0) {
            throw new ValidationException("El expediente del Documento no puede estar vacío.");
        }
        validateExpediente(doc.getExpediente());

        if (doc.getIdDestinatarios().isEmpty() && doc.getDestinatarios().isEmpty()) {
            throw new ValidationException("El documento no puede estar sin destinatarios.");
        }
    }

    public static void validateOficio(Oficio oficio) {
        if (oficio.getDocumento() == null) {
            throw new ValidationException("El documento del Oficio no puede estar vacío.");
        }
//        validateDocumento(oficio.getDocumento());
        // no necesitamos validar el documento anticipadamente. Add y Update del DocService ya validan todo lo de doc

        if (oficio.getAsunto() == null || oficio.getAsunto().isBlank()) {
            throw new ValidationException("El asunto del Ofcio no puede estar vacío.");
        }

        if (oficio.getReferencia() == null || oficio.getReferencia().isBlank()) {
            throw new ValidationException("La referencia del Ofcio no puede estar vacía.");
        }
    }
    // 
    public static void validateMemorandum(Memorandum memorandum) {
        if (memorandum.getDocumento() == null) {
            throw new ValidationException("El documento del Memorandum no puede estar vacío.");
        }
//        validateDocumento(oficio.getDocumento());
        // no necesitamos validar el documento anticipadamente. Add y Update del DocService ya validan todo lo de doc

        if (memorandum.getAsunto() == null || memorandum.getAsunto().isBlank()) {
            throw new ValidationException("El asunto del Memorandum no puede estar vacío.");
        }

        if (memorandum.getReferencia() == null || memorandum.getReferencia().isBlank()) {
            throw new ValidationException("La referencia del Memorandum no puede estar vacía.");
        }
    }
    // ActaSustentacionTesis
    public static void validateActaSust(ActaSustentacionTesis acta) {
        if (acta.getDocumento() == null) {
            throw new ValidationException("El documento del Acta de Sustentación no puede estar vacío.");
        }
//        validateDocumento(oficio.getDocumento());
        // no necesitamos validar el documento anticipadamente. Add y Update del DocService ya validan todo lo de doc

        if (acta.getTema()== null || acta.getTema().isBlank()) {
            throw new ValidationException("El asunto del Acta de Sustentación no puede estar vacío.");
        }

        if (acta.getCalificacion() <= 0 || acta.getCalificacion() > 30) {
            throw new ValidationException("La calificación del Acta de Sustentación no puede ser mayor a 30 o menor a 0.");
        }
    }
    
    public static void validateDetDestinatario(DetalleDestinatario detDest) {
        if (detDest.getDocumento() == null) {
            throw new ValidationException("El documento del Det. Destinatario no puede estar vacío.");
        }
//        validateDocumento(detDest.getDocumento());

        if (detDest.getDestinatario() == null) {
            throw new ValidationException("El destinatario del Det. Destinatario no puede estar vacío.");
        }
        validateAdministrativo(detDest.getDestinatario());
    }

    public static void validateDetExpediente(DetalleDocumento detExp) {
        if (detExp.getDocumento() == null) {
            throw new ValidationException("El documento del Det. Documento no puede estar vacío.");
        }
//        validateDocumento(detExp.getDocumento());

        if (detExp.getExpediente() == null) {
            throw new ValidationException("El expediente del Det. Documento no puede estar vacío.");
        }
        validateExpediente(detExp.getExpediente());
    }
    
    public static void validateDetSustentacion(DetalleSustentacion detSust) {
    	if (detSust.getExpediente() == null) {
    		throw new ValidationException("El exoediente del Det. Sustentacion no puede estar vacío.");
    	}
    	validateExpediente(detSust.getExpediente());
    	
    	if (detSust.getSustentacion() == null) {
    		throw new ValidationException("La sustentacion del Det. Sustentacion no puede estar vacía.");
    	}
    }
    

    public static String showWarning(String w) {
        return warningColor + w + normalColor;
    }

    public static String showInfo(String s) {
        return infoColor + s + normalColor;
    }

    public static String showInMagenta(String s) {
        return magentaColor + s + normalColor;
    }

}
