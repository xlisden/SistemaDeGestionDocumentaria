package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Persona;
import java.util.List;

public interface IPersonaRepository {

    public void addPersona(Persona persona);

    public void updatePersona(int id, Persona persona);

    public void deletePersona(int id);

    public List<Persona> getAllPersonas();

    public Persona getByIdPersona(int id);
}
