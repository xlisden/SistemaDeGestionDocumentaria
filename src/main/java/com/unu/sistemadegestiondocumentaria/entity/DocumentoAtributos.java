package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Transient;

public abstract class DocumentoAtributos {

    @Transient
    protected int idTipoDoc;

    @Transient
    protected int idEmisor;

    @Transient
    protected Date fechaEmision;

    @Transient
    protected List<Integer> idDestinatarios;

    @Transient
    protected List<Integer> idExpedientes;

    public DocumentoAtributos() {
    }

    public DocumentoAtributos(Date fechaEmision, int idTipoDoc, int idEmisor, List<Integer> idDestinatarios, List<Integer> idExpedientes) {
        this.fechaEmision = fechaEmision;
        this.idDestinatarios = idDestinatarios;
        this.idEmisor = idEmisor;
        this.idExpedientes = idExpedientes;
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public List<Integer> getIdDestinatarios() {
        return idDestinatarios;
    }

    public void setIdDestinatarios(List<Integer> idDestinatarios) {
        this.idDestinatarios = idDestinatarios;
    }

    public List<Integer> getIdExpedientes() {
        return idExpedientes;
    }

    public void setIdExpedientes(List<Integer> idExpedientes) {
        this.idExpedientes = idExpedientes;
    }

}
