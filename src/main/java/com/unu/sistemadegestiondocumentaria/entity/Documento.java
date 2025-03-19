package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
    @Transient
    private List<Integer> idDestinatarios;
    @Transient
    private List<Administrativo> destinatarios;
    @Transient
    private int idExpediente;
    @Transient
    private Expediente expediente;

    public Documento() {
        idDestinatarios = new ArrayList<>();
        destinatarios = new ArrayList<>();
    }

    //no estado porque se supone que es pendiente. A menos que el ing. ingrese los docs. una vez entregados
    public Documento(Date fechaEmision, int idTipoDoc, int idEmisor, List<Integer> idDestinatarios, int idExpediente) {
        this.fechaEmision = fechaEmision;
        this.idTipoDoc = idTipoDoc;
        this.idEmisor = idEmisor;
        this.idDestinatarios = idDestinatarios;
        destinatarios = new ArrayList<>();
        this.idExpediente = idExpediente;
    }

    public Documento(int id, String correlativo, Date fechaEmision, TipoDocumento tipoDocumento, Estado estado, Administrativo emisor) {
        this.id = id;
        this.correlativo = correlativo;
        this.fechaEmision = fechaEmision;
        this.tipoDocumento = tipoDocumento;
        this.estado = estado;
        this.emisor = emisor;
        idDestinatarios = new ArrayList<>();
        destinatarios = new ArrayList<>();
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
        return "Documento{" + "id=" + id + ", correlativo=" + correlativo + ", fechaEmision=" + fechaEmision + ", tipoDocumento=" + tipoDocumento + ", estado=" + estado + ", emisor=" + emisor + ", exp=" + expediente + '}';
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

    public List<Administrativo> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Administrativo> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public List<Integer> getIdDestinatarios() {
        return idDestinatarios;
    }

    public void setIdDestinatarios(List<Integer> idDestinatarios) {
        this.idDestinatarios = idDestinatarios;
    }

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

}
