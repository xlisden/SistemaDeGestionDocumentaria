package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-14T17:47:24", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Oficio.class)
public class Oficio_ { 

    public static volatile SingularAttribute<Oficio, String> asunto;
    public static volatile SingularAttribute<Oficio, Documento> documento;
    public static volatile SingularAttribute<Oficio, Integer> id;
    public static volatile SingularAttribute<Oficio, String> referencia;

}