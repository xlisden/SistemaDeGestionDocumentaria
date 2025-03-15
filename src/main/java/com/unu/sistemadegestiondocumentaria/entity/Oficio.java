package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "oficios")
public class Oficio extends DocumentoAtributos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String asunto;

    private String referencia;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_documento", foreignKey = @ForeignKey(name = "fk_documento_oficio"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Documento documento;

    public Oficio() {
    }

    public Oficio(Date fechaEmision, int idTipoDoc, int idEmisor, List<Integer> idDestinatarios, String asunto, String referencia) {
        super(fechaEmision, idTipoDoc, idEmisor, idDestinatarios);
        this.asunto = asunto;
        this.referencia = referencia;
        this.documento = new Documento(fechaEmision, idTipoDoc, idEmisor, idDestinatarios);
    }

    public Oficio(Date fechaEmision, int idTipoDoc, int idEmisor, List<Integer> idDestinatarios, int idExpediente, String asunto, String referencia) {
        super(fechaEmision, idTipoDoc, idEmisor, idDestinatarios, idExpediente);
        this.asunto = asunto;
        this.referencia = referencia;
        this.documento = new Documento(fechaEmision, idTipoDoc, idEmisor, idDestinatarios, idExpediente);
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

}
