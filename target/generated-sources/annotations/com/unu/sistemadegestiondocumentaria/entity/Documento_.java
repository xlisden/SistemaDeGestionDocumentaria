package com.unu.sistemadegestiondocumentaria.entity;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import java.sql.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-03-13T18:24:01", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Documento.class)
public class Documento_ { 

    public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Documento, Estado> estado;
    public static volatile SingularAttribute<Documento, String> correlativo;
    public static volatile SingularAttribute<Documento, Date> fechaEmision;
    public static volatile SingularAttribute<Documento, Integer> id;
    public static volatile SingularAttribute<Documento, Administrativo> emisor;

}