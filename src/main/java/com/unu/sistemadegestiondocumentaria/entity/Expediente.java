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
@Table(name = "expedientes")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nroExpediente;

    @OneToOne
    @JoinColumn(name = "id_egresado", foreignKey = @ForeignKey(name = "fk_egresado_expediente"))
    private Egresado egresado;

    // @Transient
    // private String nombre;
    // @Transient
    // private String apellidoPaterno;
    // @Transient
    // private String apellidoMaterno;
    // @Transient
    // private GradoInstruccion gradoInstruccion;    

    public Expediente() {
    }

    public Expediente(Egresado egresado) {
        this.egresado = egresado;
    }

    public Expediente(int nroExpediente, Egresado egresado) {
        this.nroExpediente = nroExpediente;
        this.egresado = egresado;
    }

    public Expediente(int id, int nroExpediente, Egresado egresado) {
        this.id = id;
        this.nroExpediente = nroExpediente;
        this.egresado = egresado;
    }

    // public Expediente(String nombre, String apellidoPaterno, String apellidoMaterno, GradoInstruccion gradoInstruccion) {
    //     this.nombre = nombre;
    //     this.apellidoPaterno = apellidoPaterno;
    //     this.apellidoMaterno = apellidoMaterno;
    //     this.gradoInstruccion = gradoInstruccion;
    //     this.egresado = new Egresado(new Persona(nombre, apellidoPaterno, apellidoMaterno, gradoInstruccion));
    // }

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

    public Egresado getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresado egresado) {
        this.egresado = egresado;
    }

    @Override
    public String toString() {
        return "Expediente{" + "id=" + id + ", nroExpediente=" + nroExpediente + ", egresado=" + egresado + '}';
    }

    // public String getNombre() {
    //     return nombre;
    // }

    // public void setNombre(String nombre) {
    //     this.nombre = nombre;
    // }

    // public String getApellidoPaterno() {
    //     return apellidoPaterno;
    // }

    // public void setApellidoPaterno(String apellidoPaterno) {
    //     this.apellidoPaterno = apellidoPaterno;
    // }

    // public String getApellidoMaterno() {
    //     return apellidoMaterno;
    // }

    // public void setApellidoMaterno(String apellidoMaterno) {
    //     this.apellidoMaterno = apellidoMaterno;
    // }

    // public GradoInstruccion getGradoInstruccion() {
    //     return gradoInstruccion;
    // }

    // public void setGradoInstruccion(GradoInstruccion gradoInstruccion) {
    //     this.gradoInstruccion = gradoInstruccion;
    // }

}
