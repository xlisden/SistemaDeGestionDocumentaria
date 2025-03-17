package com.unu.sistemadegestiondocumentaria.validations;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
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
            throw new ValidationException(showWarning("El nombre del Grado de Instrucción no puede estar vacío."));
        }
    }

    public static void validateTipoDocumento(TipoDocumento td) {
        if (td.getNombre() == null || td.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre del Tipo de Documento no puede estar vacío."));
        }
    }

    public static void validateEstado(Estado e) {
        if (e.getNombre() == null || e.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre del Estado no puede estar vacío."));
        }
    }

    public static void validatePersona(Persona p) {
        if (p == null) {
            throw new ValidationException(showWarning("La Persona no puede estar vacía."));
        } // p.getNombre() == null ||

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre de la Persona no puede estar vacío."));
        }

        if (p.getApellidoPaterno() == null || p.getApellidoPaterno().isBlank()) {
            throw new ValidationException(showWarning("El apellido paterno de la Persona no puede estar vacío."));
        }

        if (p.getApellidoMaterno() == null || p.getApellidoMaterno().isBlank()) {
            throw new ValidationException(showWarning("El apellido materno de la Persona no puede estar vacío."));
        }

        if (p.getGradoInstruccion() == null) {
            throw new ValidationException(showWarning("El Grado de Instrucción de la Persona no puede estar vacío."));
        }

        validateGradoInstruccion(p.getGradoInstruccion());
    }

    public static void validateEgresado(Egresado e) {
        if (e.getPersona() == null) {
            throw new ValidationException(showWarning("La Persona del Egresado no puede estar vacía."));
        }
        validatePersona(e.getPersona());
    }

    public static void validateAdministrativo(Administrativo ad) {
        if (ad.getPersona() == null) {
            throw new ValidationException(showWarning("La Persona del Administrativo no puede estar vacía."));
        }
        validatePersona(ad.getPersona());
    }

    public static void validateExpediente(Expediente exp) {
        if (exp.getEgresado() == null) {
            throw new ValidationException(showWarning("El Egresado del Expediente no puede estar vacío"));
        }
        validateEgresado(exp.getEgresado());
    }

    public static void validateDocumento(Documento doc) {
        if (doc.getFechaEmision() == null || doc.getFechaEmision().toString().isBlank()) {
            throw new ValidationException(showWarning("La fecha de emisión del Documento no puede estar vacía."));
        }

        if (doc.getTipoDocumento() == null) {
            throw new ValidationException(showWarning("El tipo de documento del Documento no puede estar vacío."));
        }
        validateTipoDocumento(doc.getTipoDocumento());

        if (doc.getEstado() == null) {
            throw new ValidationException(showWarning("El estado del Documento no puede estar vacío."));
        }
        validateEstado(doc.getEstado());

        if (doc.getEmisor() == null) {
            throw new ValidationException(showWarning("El emisor del Documento no puede estar vacío."));
        }
        validateAdministrativo(doc.getEmisor());

        if (doc.getExpediente() == null) {
            throw new ValidationException(showWarning("El expediente del Documento no puede estar vacío."));
        }
        validateExpediente(doc.getExpediente());

        if (doc.getIdDestinatarios().isEmpty() && doc.getDestinatarios().isEmpty()) {
            throw new ValidationException(showWarning("El documento no puede estar sin destinatarios."));
        }
    }

    public static void validateOficio(Oficio oficio) {
        if (oficio.getDocumento() == null) {
            throw new ValidationException(showWarning("El documento del Oficio no puede estar vacío."));
        }
        validateDocumento(oficio.getDocumento());

        if (oficio.getAsunto() == null || oficio.getAsunto().isBlank()) {
            throw new ValidationException(showWarning("El asunto del Ofcio no puede estar vacío."));
        }

        if (oficio.getReferencia() == null || oficio.getReferencia().isBlank()) {
            throw new ValidationException(showWarning("La referencia del Documento no puede estar vacía."));
        }
    }

    public static void validateDetDestinatario(DetalleDestinatario detDest) {
        if (detDest.getDocumento() == null) {
            throw new ValidationException(showWarning("El documento del Det. Destinatario no puede estar vacío."));
        }
        validateDocumento(detDest.getDocumento());

        if (detDest.getDestinatario() == null) {
            throw new ValidationException(showWarning("El destinatario del Det. Destinatario no puede estar vacío."));
        }
        validateAdministrativo(detDest.getDestinatario());
    }

    public static void validateDetExpediente(DetalleDocumento detExp) {
        if (detExp.getDocumento() == null) {
            throw new ValidationException(showWarning("El documento del Det. Documento no puede estar vacío."));
        }
        validateDocumento(detExp.getDocumento());

        if (detExp.getExpediente() == null) {
            throw new ValidationException(showWarning("El exoediente del Det. Documento no puede estar vacío."));
        }
        validateExpediente(detExp.getExpediente());
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
