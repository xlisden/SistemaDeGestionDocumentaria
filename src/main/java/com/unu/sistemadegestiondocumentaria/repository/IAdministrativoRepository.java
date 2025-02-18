package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import java.util.List;

public interface IAdministrativoRepository {

    public void addAdministrativo(Administrativo administrativo);

    public void updateAdministrativo(int id, Administrativo administrativo);

    public void deleteAdministrativo(int id);

    public List<Administrativo> getAllAdministrativos();

    public Administrativo getByIdAdministrativo(int id);
}
