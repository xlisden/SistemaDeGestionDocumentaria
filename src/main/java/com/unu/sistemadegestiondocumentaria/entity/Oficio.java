package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "oficios")
public class Oficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String asunto;

    private String referencia;

    @OneToOne
    @JoinColumn(name = "id_documento", foreignKey = @ForeignKey(name = "fk_documento_oficio"))
    private Documento documento;

    @Transient
    private int idTipoDoc;
    @Transient
    private int idEmisor;
    @Transient
    private Date fechaEmisor;

    public Oficio() {
    }

    public Oficio(Date fechaEmision, int idTipoDoc, int idEmisor, String asunto, String referencia) {
        this.asunto = asunto;
        this.referencia = referencia;
        this.documento = new Documento(fechaEmision, idTipoDoc, idEmisor);
    }

    public Oficio(int id, String asunto, String referencia, Documento documento) {
        this.id = id;
        this.asunto = asunto;
        this.referencia = referencia;
        this.documento = documento;
    }

    public Oficio(String asunto, String referencia, Documento documento) {
        this.asunto = asunto;
        this.referencia = referencia;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Oficio{" + "id=" + id + ", asunto=" + asunto + ", referencia=" + referencia + ", documento=" + documento + '}';
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

    public Date getFechaEmisor() {
        return fechaEmisor;
    }

    public void setFechaEmisor(Date fechaEmisor) {
        this.fechaEmisor = fechaEmisor;
    }

}
