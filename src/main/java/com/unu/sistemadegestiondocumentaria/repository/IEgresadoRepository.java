package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import java.util.List;

public interface IEgresadoRepository {

    public void addEgresado(Egresado egresado);

    public void updateEgresado(int id, Egresado egresado);

    public void deleteEgresado(int id);

    public List<Egresado> getAllEgresados();

    public Egresado getByIdEgresado(int id);
}
