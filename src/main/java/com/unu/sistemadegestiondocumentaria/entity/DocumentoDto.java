package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Para mostrar Datos
public class DocumentoDto {

    private int id;

    private Documento doc;

    private List<Administrativo> destinatarios;

    List<Integer> idExpedientes;

    private List<Expediente> expedientes;

    public DocumentoDto() {
        destinatarios = new ArrayList<>();
        expedientes = new ArrayList<>();
    }

    public DocumentoDto(Documento doc, List<Administrativo> destinatarios, List<Expediente> expedientes, List<Integer> idExpedientes) {
        this.doc = doc;
        this.destinatarios = destinatarios;
        this.expedientes = expedientes;
        this.idExpedientes = idExpedientes;
    }

    public DocumentoDto(int id, String correlativo, Date fechaEmision, TipoDocumento tipoDocumento, Estado estado,
            Administrativo emisor, List<Administrativo> destinatarios, List<Expediente> expedientes) {
        doc = new Documento(id, correlativo, fechaEmision, tipoDocumento, estado, emisor);
        this.destinatarios = destinatarios;
        this.expedientes = expedientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public List<Administrativo> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Administrativo> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public List<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(List<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    public List<Integer> getIdExpedientes() {
        return idExpedientes;
    }

    public void setIdExpedientes(List<Integer> idExpedientes) {
        this.idExpedientes = idExpedientes;
    }

    @Override
    public String toString() {
        return "DocumentoDto [id=" + id + ", doc=" + doc + ", destinatarios=" + destinatarios + ", expedientes="
                + expedientes + "]";
    }

    public String getNombre() {
        return doc.getTipoDocumento().getNombre() + " " + doc.getCorrelativo();
    }

    public String getFechaEmision() {
        return doc.getFechaEmision().toString();
    }

}
