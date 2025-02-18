package com.unu.sistemadegestiondocumentaria.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Egresado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "fk_persona_egresado"))
    private Persona persona;

    public Egresado() {
    }

    public Egresado(Persona persona) {
        this.persona = persona;
    }

    public Egresado(int id, Persona persona) {
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

}
