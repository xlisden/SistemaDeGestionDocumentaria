package com.unu.sistemadegestiondocumentaria.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalles_documento")
public class DetalleDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_documento", foreignKey = @ForeignKey(name = "fk_documento_detalle_documento"))
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "id_expediente", foreignKey = @ForeignKey(name = "fk_expediente_detalle_documento"))
    private Expediente expediente;

    public DetalleDocumento() {
    }

    public DetalleDocumento(Documento documento, Expediente expediente) {
        this.documento = documento;
        this.expediente = expediente;
    }

    public DetalleDocumento(int id, Documento documento, Expediente expediente) {
        this.id = id;
        this.documento = documento;
        this.expediente = expediente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @Override
    public String toString() {
        return "DetalleDocumento{" + "id=" + id + ", documento=" + documento + ", expediente=" + expediente + '}';
    }

}
