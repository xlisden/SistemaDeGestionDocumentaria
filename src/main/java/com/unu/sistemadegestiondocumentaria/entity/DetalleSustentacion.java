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
@Table(name = "detalle_sustentaciones")
public class DetalleSustentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_sustentacion", foreignKey = @ForeignKey(name = "fk_sustentacion_detalle_sustentacion"))
    private Sustentacion sustentacion;

    @ManyToOne
    @JoinColumn(name = "id_expediente", foreignKey = @ForeignKey(name = "fk_expediente_detalle_sustentacion"))
    private Expediente expediente;

    @Transient
    private int idExp;
    @Transient
    private int idSust;

    public DetalleSustentacion() {
    }

    public DetalleSustentacion(int idExp, int idSust) {
        this.idExp = idExp;
        this.idSust = idSust;
    }

    public DetalleSustentacion(Sustentacion sustentacion, Expediente expediente) {
        this.sustentacion = sustentacion;
        this.expediente = expediente;
    }

    public DetalleSustentacion(int id, Sustentacion sustentacion, Expediente expediente) {
        this.id = id;
        this.sustentacion = sustentacion;
        this.expediente = expediente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Sustentacion getSustentacion() {
        return sustentacion;
    }

    public void setSustentacion(Sustentacion sustentacion) {
        this.sustentacion = sustentacion;
    }

    @Override
    public String toString() {
        return "DetalleSustentacion{" + "id=" + id + ", sustentacion=" + sustentacion + ", expediente=" + expediente + '}';
    }

    public int getIdExp() {
        return idExp;
    }

    public void setIdExp(int idExp) {
        this.idExp = idExp;
    }

    public int getIdSust() {
        return idSust;
    }

    public void setIdSust(int idSust) {
        this.idSust = idSust;
    }

}
