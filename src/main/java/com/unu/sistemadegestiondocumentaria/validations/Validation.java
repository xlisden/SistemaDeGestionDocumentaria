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
    public static String magentaColor  = "\033[35m";

    public void validateGradoInstruccion(GradoInstruccion gi) {
        if (gi.getNombre() == null || gi.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre del Grado de Instrucción no puede estar vacío."));
        }
    }

    public void validateTipoDocumento(TipoDocumento td) {
        if (td.getNombre() == null || td.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre del Tipo de Documento no puede estar vacío."));
        }
    }

    public void validateEstado(Estado e) {
        if (e.getNombre() == null || e.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre del Estado no puede estar vacío."));
        }
    }

    public void validatePersona(Persona p) {
        if(p == null){
            throw new ValidationException(showWarning("La Persona no puede estar vacía."));
        } //p.getNombre() == null || 
        
        if (p.getNombre().isBlank()) {
            throw new ValidationException(showWarning("El nombre de la Persona no puede estar vacío."));
        }
        
        if (p.getApellidoPaterno().isBlank()) {
            throw new ValidationException(showWarning("El apellido paterno de la Persona no puede estar vacío."));
        }
        
        if (p.getApellidoMaterno().isBlank()) {
            throw new ValidationException(showWarning("El apellido materno de la Persona no puede estar vacío."));
        }
        
        if (p.getGradoInstruccion() == null) {
            throw new ValidationException(showWarning("El Grado de Instrucción de la Persona no puede estar vacío."));
        }
        
        validateGradoInstruccion(p.getGradoInstruccion() );
    }

    public void validateEgresado(Egresado e) {
        if(e.getPersona() == null){
            throw new ValidationException(showWarning("La Persona del Egresado no puede estar vacía."));
        }
        validatePersona(e.getPersona());
    }

    public void validateAdministrativo(Administrativo ad) {
        if(ad.getPersona() == null){
            throw new ValidationException(showWarning("La Persona del Administrativo no puede estar vacía."));
        }
        validatePersona(ad.getPersona());
    }

    public void validateExpediente(Expediente e) {
        if(e.getEgresado()== null){
            throw new ValidationException(showWarning("El Egresado del Expediente no puede estar vacío"));
        }
        validateEgresado(e.getEgresado());
    }

    public void validateDocumento(Documento doc) {
        if (doc.getFechaEmision() == null) {
            throw new ValidationException(showWarning("La fecha de emisión del Documento no puede estar vacía." ));
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
    }
    
    public static String showWarning(String w){
        return warningColor + w + normalColor;
    }

}
