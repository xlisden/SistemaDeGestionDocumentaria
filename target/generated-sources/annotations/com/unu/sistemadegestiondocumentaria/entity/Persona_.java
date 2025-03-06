package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-05T22:33:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidoPaterno;
    public static volatile SingularAttribute<Persona, GradoInstruccion> gradoInstruccion;
    public static volatile SingularAttribute<Persona, Integer> id;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> apellidoMaterno;

}