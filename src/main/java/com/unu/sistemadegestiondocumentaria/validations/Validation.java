package com.unu.sistemadegestiondocumentaria.validations;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;

public class Validation {

    public static String warningColor = "\033[43m";
    public static String normalColor = "\033[0m";

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
        Persona p = e.getPersona();
        validatePersona(p);
    }
}
