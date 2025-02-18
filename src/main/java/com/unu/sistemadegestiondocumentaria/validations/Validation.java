package com.unu.sistemadegestiondocumentaria.validations;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;

public class Validation {

    public static String warningColor = "\033[43m";
    public static String normalColor = "\033[0m";
    public static String infoColor = "\033[34m";

    public void validateGradoInstruccion(GradoInstruccion gi) {
        if (gi.getNombre().isBlank()) {
            throw new ValidationException(warningColor + "El nombre del Grado de Instrucción no puede estar vacío." + normalColor);
        }
    }

    public void validateTipoDocumento(TipoDocumento td) {
        if (td.getNombre().isBlank()) {
            throw new ValidationException(warningColor + "El nombre del Tipo de Documento no puede estar vacío." + normalColor);
        }
    }

    public void validateEstado(Estado e) {
        if (e.getNombre().isBlank()) {
            throw new ValidationException(warningColor + "El nombre del Estado no puede estar vacío." + normalColor);
        }
    }

    public void validatePersona(Persona p) {
        if (p.getNombre().isBlank()) {
            throw new ValidationException(warningColor + "El nombre de la Persona no puede estar vacío." + normalColor);
        }
        
        if (p.getApellidoPaterno().isBlank()) {
            throw new ValidationException(warningColor + "El apellido paterno de la Persona no puede estar vacío." + normalColor);
        }
        
        if (p.getApellidoMaterno().isBlank()) {
            throw new ValidationException(warningColor + "El apellido materno de la Persona no puede estar vacío." + normalColor);
        }
        
        if (p.getGradoInstruccion() == null) {
            throw new ValidationException(warningColor + "El Grado de Instrucción de la Persona no puede estar vacío." + normalColor);
        }
    }

    public void validateEgresado(Egresado e) {
        validatePersona(e.getPersona());
    }

    public void validateAdministrativo(Administrativo ad) {
        validatePersona(ad.getPersona());
    }

    public void validateExpediente(Expediente e) {
        validateEgresado(e.getEgresado());
    }

    public void validateDocumento(Documento doc) {
        if (doc.getFechaEmision() == null) {
            throw new ValidationException(warningColor + "La fecha de emisión del Documento no puede estar vacía." + normalColor);
        }
        
        if (doc.getTipoDocumento() == null) {
            throw new ValidationException(warningColor + "El tipo de documento del Documento no puede estar vacío." + normalColor);
        } else {
            validateTipoDocumento(doc.getTipoDocumento());
        }
        
        if (doc.getEstado() == null) {
            throw new ValidationException(warningColor + "El estado del Documento no puede estar vacío." + normalColor);
        } else {
            validateEstado(doc.getEstado());
        }

        if (doc.getEmisor() == null) {
            throw new ValidationException(warningColor + "El emisor del Documento no puede estar vacío." + normalColor);
        } else {
            validateAdministrativo(doc.getEmisor());
        }
    }

}
