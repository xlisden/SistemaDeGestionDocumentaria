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
@Table(name = "actas_sustentacion_tesis")
public class ActaSustentacionTesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tema;

    private int calificacion; //validacion de menor y mayor

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_documento", foreignKey = @ForeignKey(name = "fk_documento_acta"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Documento documento;

    public ActaSustentacionTesis() {
    }

    public ActaSustentacionTesis(String tema, int calificacion, Documento documento) {
        this.tema = tema;
        this.calificacion = calificacion;
        this.documento = documento;
    }

    public ActaSustentacionTesis(int id, String tema, int calificacion, Documento documento) {
        this.id = id;
        this.tema = tema;
        this.calificacion = calificacion;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "ActaSustentacionTesis{" + "id=" + id + ", tema=" + tema + ", calificacion=" + calificacion + ", documento=" + documento + '}';
    }

}
