package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Estado;
import java.util.List;

public interface IEstadoRepository {

    public void addEstado(Estado estado);

    public void updateEstado(int id, Estado estado);

    public void deleteEstado(int id);

    public List<Estado> getAllEstados();

    public Estado getByIdEstado(int id);
}
