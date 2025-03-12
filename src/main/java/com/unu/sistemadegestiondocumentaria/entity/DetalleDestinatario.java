package com.unu.sistemadegestiondocumentaria.entity;

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
@Table(name = "detalles_destinatario")
public class DetalleDestinatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_documento", foreignKey = @ForeignKey(name = "fk_documento_detalle_destinatario"))
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "id_destinatario", foreignKey = @ForeignKey(name = "fk_destinatario_detalle_destinatario"))
    private Administrativo destinatario;

    @Transient
    private int idDoc;
    @Transient
    private int idDest;

    public DetalleDestinatario() {
    }

    public DetalleDestinatario(Documento documento, Administrativo destinatario) {
        this.documento = documento;
        this.destinatario = destinatario;
    }

    public DetalleDestinatario(int idDoc, int idDest) {
        this.idDoc = idDoc;
        this.idDest = idDest;
    }

    public DetalleDestinatario(int id, Documento documento, Administrativo destinatario) {
        this.id = id;
        this.documento = documento;
        this.destinatario = destinatario;
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

    public Administrativo getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Administrativo destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "DetalleDestinatario{" + "id=" + id + ", documento=" + documento + ", destinatario=" + destinatario + '}';
    }

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public int getIdDest() {
        return idDest;
    }

    public void setIdDest(int idDest) {
        this.idDest = idDest;
    }
    
    

}
