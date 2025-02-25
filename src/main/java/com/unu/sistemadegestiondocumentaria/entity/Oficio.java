package com.unu.sistemadegestiondocumentaria.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    public Oficio() {
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
