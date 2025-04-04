package com.unu.sistemadegestiondocumentaria.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Para mostrar Datos
public class DocumentoDto {

	private int id;

	private String correlativo;

	private Date fechaEmision;

	private TipoDocumento tipoDocumento;

	private Estado estado;

	private Administrativo emisor;

	private List<Administrativo> destinatarios;

	private List<Expediente> expedientes;

	public DocumentoDto() {
		destinatarios = new ArrayList<>();
		expedientes = new ArrayList<>();
	}

	public DocumentoDto(Date fechaEmision, TipoDocumento tipoDoc, Administrativo emisor, List<Administrativo> destinatarios, List<Expediente> expedientes) {
		this.fechaEmision = fechaEmision;
		this.tipoDocumento = tipoDoc;
		this.emisor = emisor;
		this.destinatarios = destinatarios;
		this.expedientes = expedientes;
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
		return "Documento{" + "id=" + id + ", correlativo=" + correlativo + ", fechaEmision=" + fechaEmision
				+ ", tipoDocumento=" + tipoDocumento + ", estado=" + estado + ", emisor=" + emisor + '}';
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

	public String getNombre() {
		return tipoDocumento.getNombre() + " " + correlativo;
	}

}
