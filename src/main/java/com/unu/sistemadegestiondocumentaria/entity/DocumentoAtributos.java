package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Transient;

public class DocumentoAtributos {
    @Transient
    private int idTipoDoc;
    @Transient
    private int idEmisor;
    @Transient
    private Date fechaEmision;
    @Transient
    private List<Integer> idDestinatarios;

    public DocumentoAtributos() {
    }

    public DocumentoAtributos(Date fechaEmision, List<Integer> idDestinatarios, int idEmisor, int idTipoDoc) {
        this.fechaEmision = fechaEmision;
        this.idDestinatarios = idDestinatarios;
        this.idEmisor = idEmisor;
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


}
