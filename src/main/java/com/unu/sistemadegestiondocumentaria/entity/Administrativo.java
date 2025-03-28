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
@Table(name = "administrativos")
public class Administrativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "fk_persona_administrativo"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Persona persona;

    public Administrativo() {
    }

    public Administrativo(Persona persona) {
        this.persona = persona;
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
//        return "Administrativo{" + "id=" + id + ", persona=" + persona + '}';
        String s = (persona != null) ? persona.getGradoInstruccion().getNombre() + " " + persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno() : "";
        return s;
    }

}
