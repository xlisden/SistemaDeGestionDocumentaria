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
@Table(name = "sustentaciones")
public class Sustentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "id_jurado1", foreignKey = @ForeignKey(name = "fk_jurado1_sustentacion"))
    private Administrativo jurado1;

    @ManyToOne()
    @JoinColumn(name = "id_jurado2", foreignKey = @ForeignKey(name = "fk_jurado2_sustentacion"))
    private Administrativo jurado2;

    @ManyToOne()
    @JoinColumn(name = "id_jurado3", foreignKey = @ForeignKey(name = "fk_jurado3_sustentacion"))
    private Administrativo jurado3;

    @ManyToOne()
    @JoinColumn(name = "id_asesor", foreignKey = @ForeignKey(name = "fk_asesor_sustentacion"))
    private Administrativo asesor;

    public Sustentacion() {
    }

    public Sustentacion(Administrativo jurado1, Administrativo jurado2, Administrativo jurado3, Administrativo asesor) {
        this.jurado1 = jurado1;
        this.jurado2 = jurado2;
        this.jurado3 = jurado3;
        this.asesor = asesor;
    }

    public Sustentacion(int id, Administrativo jurado1, Administrativo jurado2, Administrativo jurado3, Administrativo asesor) {
        this.id = id;
        this.jurado1 = jurado1;
        this.jurado2 = jurado2;
        this.jurado3 = jurado3;
        this.asesor = asesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Administrativo getJurado1() {
        return jurado1;
    }

    public void setJurado1(Administrativo jurado1) {
        this.jurado1 = jurado1;
    }

    public Administrativo getJurado2() {
        return jurado2;
    }

    public void setJurado2(Administrativo jurado2) {
        this.jurado2 = jurado2;
    }

    public Administrativo getJurado3() {
        return jurado3;
    }

    public void setJurado3(Administrativo jurado3) {
        this.jurado3 = jurado3;
    }

    public Administrativo getAsesor() {
        return asesor;
    }

    public void setAsesor(Administrativo asesor) {
        this.asesor = asesor;
    }

    @Override
    public String toString() {
        return "Sustentacion{" + "id=" + id + ", jurado1=" + jurado1 + ", jurado2=" + jurado2 + ", jurado3=" + jurado3 + ", asesor=" + asesor + '}';
    }

}
