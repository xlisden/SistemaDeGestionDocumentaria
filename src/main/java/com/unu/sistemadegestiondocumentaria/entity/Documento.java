package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String correlativo;

    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", foreignKey = @ForeignKey(name = "fk_tipo_documento_documento"))
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_estado", foreignKey = @ForeignKey(name = "fk_estado_documento"))
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_emisor", foreignKey = @ForeignKey(name = "fk_emisor_documento"))
    private Administrativo emisor;

    @Transient
    private int idTipoDoc;
    @Transient
    private int idEstado;
    @Transient
    private int idEmisor;

    public Documento() {
    }

    public Documento(Date fechaEmision, int idTipoDoc, int idEstado, int idEmisor) {
        this.fechaEmision = fechaEmision;
        this.idTipoDoc = idTipoDoc;
        this.idEstado = idEstado;
        this.idEmisor = idEmisor;
    }

    public Documento(Date fechaEmision, TipoDocumento tipoDocumento, Estado estado, Administrativo emisor) {
        this.fechaEmision = fechaEmision;
        this.tipoDocumento = tipoDocumento;
        this.estado = estado;
        this.emisor = emisor;
    }

    public Documento(String correlativo, Date fechaEmision, TipoDocumento tipoDocumento, Estado estado, Administrativo emisor) {
        this.correlativo = correlativo;
        this.fechaEmision = fechaEmision;
        this.tipoDocumento = tipoDocumento;
        this.estado = estado;
        this.emisor = emisor;
    }

    public Documento(int id, String correlativo, Date fechaEmision, TipoDocumento tipoDocumento, Estado estado, Administrativo emisor) {
        this.id = id;
        this.correlativo = correlativo;
        this.fechaEmision = fechaEmision;
        this.tipoDocumento = tipoDocumento;
        this.estado = estado;
        this.emisor = emisor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Administrativo getEmisor() {
        return emisor;
    }

    public void setEmisor(Administrativo emisor) {
        this.emisor = emisor;
    }

    @Override
    public String toString() {
        return "Documento{" + "id=" + id + ", correlativo=" + correlativo + ", fechaEmision=" + fechaEmision + ", tipoDocumento=" + tipoDocumento + ", estado=" + estado + ", emisor=" + emisor + '}';
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

}
