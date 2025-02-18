package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import java.util.List;

public interface IGradoInstruccionRepository {

    public void addGradoInstruccion(GradoInstruccion gradoInstruccion);

    public void updateGradoInstruccion(int id, GradoInstruccion gradoInstruccion);

    public void deleteGradoInstruccion(int id);

    public List<GradoInstruccion> getAllGradoInstrucciones();

    public GradoInstruccion getByIdGradoInstruccion(int id);
}
