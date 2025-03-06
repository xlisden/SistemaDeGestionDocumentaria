package com.unu.sistemadegestiondocumentaria.entity;

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
@Table(name = "administrativos")
public class Administrativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "fk_persona_administrativo"))
    private Persona persona;

    @Transient
    private String nombre;
    @Transient
    private String apellidoPaterno;
    @Transient
    private String apellidoMaterno;
    @Transient
    private GradoInstruccion gradoInstruccion;

    public Administrativo() {
    }

    public Administrativo(Persona persona) {
        this.persona = persona;
    }

    public Administrativo(String nombre, String apellidoPaterno, String apellidoMaterno, GradoInstruccion gradoInstruccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.gradoInstruccion = gradoInstruccion;
        this.persona = new Persona(nombre, apellidoPaterno, apellidoMaterno, gradoInstruccion);
    }

    public Administrativo(int id, Persona persona) {
        this.id = id;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Administrativo{" + "id=" + id + ", persona=" + persona + '}';
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

    

}
