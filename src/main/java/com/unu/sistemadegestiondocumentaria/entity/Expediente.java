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
    @JoinColumn(name = "id_egresado", foreignKey = @ForeignKey(name = "fk_egresado_expediente"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Egresado egresado;

    public Expediente() {
    }

    public Expediente(Egresado egresado) {
        this.egresado = egresado;
    }

    public Expediente(int id, int nroExpediente, Egresado egresado) {
        this.id = id;
        this.nroExpediente = nroExpediente;
        this.egresado = egresado;
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

    public Egresado getEgresado() {
        return egresado;
    }

    public void setEgresado(Egresado egresado) {
        this.egresado = egresado;
    }

    @Override
    public String toString() {
//        return "Expediente{" + "id=" + id + ", nroExpediente=" + nroExpediente + ", egresado=" + egresado + '}';
        String s = (egresado != null) ? egresado.getPersona().getNombre() + " " + egresado.getPersona().getApellidoPaterno() + " " + egresado.getPersona().getApellidoMaterno() : "";
        return s;
    }

    public String toStringPorAp() {
        String s = (egresado != null) ? egresado.getPersona().getApellidoPaterno().toUpperCase() + " " + egresado.getPersona().getApellidoMaterno().toUpperCase() + ", " + egresado.getPersona().getNombre() : "";
        return s;
    }
}
