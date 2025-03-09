package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-08T21:27:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetalleDestinatario.class)
public class DetalleDestinatario_ { 

    public static volatile SingularAttribute<DetalleDestinatario, Documento> documento;
    public static volatile SingularAttribute<DetalleDestinatario, Integer> id;
    public static volatile SingularAttribute<DetalleDestinatario, Administrativo> destinatario;

}