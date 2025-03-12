package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-12T18:38:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetalleDocumento.class)
public class DetalleDocumento_ { 

    public static volatile SingularAttribute<DetalleDocumento, Expediente> expediente;
    public static volatile SingularAttribute<DetalleDocumento, Documento> documento;
    public static volatile SingularAttribute<DetalleDocumento, Integer> id;

}