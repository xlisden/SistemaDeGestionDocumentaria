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
@Table(name = "personas")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	private String apellidoPaterno;

	private String apellidoMaterno;

	@Transient
	private int idGradoInst;

	@ManyToOne
	@JoinColumn(name = "id_grado_instruccion", foreignKey = @ForeignKey(name = "fk_grado_instruccion_persona"))
	private GradoInstruccion gradoInstruccion;

	public Persona() {
	}

	public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int idGradoInst) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.idGradoInst = idGradoInst;
	}

	public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, GradoInstruccion gradoInstruccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.gradoInstruccion = gradoInstruccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public GradoInstruccion getGradoInstruccion() {
		return gradoInstruccion;
	}

	public void setGradoInstruccion(GradoInstruccion gradoInstruccion) {
		this.gradoInstruccion = gradoInstruccion;
	}

	@Override
	public String toString() {
		return ((gradoInstruccion != null) ? this.gradoInstruccion.getNombre() + " " : "")
				+ this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
	}

	public int getIdGradoInst() {
		return idGradoInst;
	}

	public void setIdGradoInst(int idGradoInst) {
		this.idGradoInst = idGradoInst;
	}

}
