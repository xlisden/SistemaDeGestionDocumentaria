package com.unu.sistemadegestiondocumentaria.repository;

import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import java.util.List;

public interface IExpedienteRepository {

    public void addExpediente(Expediente expediente);

    public void updateExpediente(int id, Expediente expediente);

    public void deleteExpediente(int id);

    public List<Expediente> getAllExpedientes();

    public Expediente getByIdExpediente(int id);
}
