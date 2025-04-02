package com.unu.sistemadegestiondocumentaria.entity;

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
@Table(name = "expedientes")
public class Expediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int nroExpediente;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "fk_persona_expediente"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Persona persona;

	public Expediente() {
	}

	public Expediente(Persona persona) {
		this.persona = persona;
	}

	public Expediente(int id, int nroExpediente, Persona persona) {
		this.id = id;
		this.nroExpediente = nroExpediente;
		this.persona = persona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNroExpediente() {
		return nroExpediente;
	}

	public void setNroExpediente(int nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
    	return (persona != null) ? 
    			((this.persona.getGradoInstruccion() != null) ? this.persona.getGradoInstruccion().getNombre() + " " : "")
    				+ persona.getNombre() + " " + persona.getApellidoPaterno().toUpperCase() + " " + persona.getApellidoMaterno().toUpperCase() : "";
	}

	public String toStringPorAp() {
    	return (persona != null) ? 
    			((this.persona.getGradoInstruccion() != null) ? this.persona.getGradoInstruccion().getNombre() + " " : "") 
    				+ persona.getApellidoPaterno().toUpperCase() + " " + persona.getApellidoMaterno().toUpperCase() + ", "+ persona.getNombre() : "";
	}

}
