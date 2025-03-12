package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-12T17:54:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Memorandum.class)
public class Memorandum_ { 

    public static volatile SingularAttribute<Memorandum, String> asunto;
    public static volatile SingularAttribute<Memorandum, Documento> documento;
    public static volatile SingularAttribute<Memorandum, Integer> id;
    public static volatile SingularAttribute<Memorandum, String> referencia;

}