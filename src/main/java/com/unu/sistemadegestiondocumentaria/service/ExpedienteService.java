package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import static com.unu.sistemadegestiondocumentaria.validations.Validation.showWarning;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class ExpedienteService extends Repository<Expediente> {

    private PersonaService personaService = new PersonaService(Persona.class);
    private EgresadoService egresadoService = new EgresadoService(Egresado.class);
    private Validation validaciones = new Validation();

    public ExpedienteService(Class<Expediente> type) {
        super(type);
    }

    @Override
    public void add(Expediente t) {
        try {
            validaciones.validateExpediente(t);
            super.add(t);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Expediente t) {
        try {
            validaciones.validateExpediente(t);
            Expediente exp = getById(id);
            if (exp == null) {
                throw new ValidationException(showWarning("El Expediente no puede estar vacío."));
            }
            exp.setEgresado(t.getEgresado());
            super.update(id, exp);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    //tambien podriamos cambiar los datos del egresado del expediente, directamente desde el expediente
    public void updateEgresado(int idEg, Egresado t) {
        egresadoService.update(idEg, t);
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public List<Expediente> getAll() {
        String query = "SELECT exp FROM Expediente exp";
        return super.getAll(query);
    }

    @Override
    public Expediente getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null; 
    }

}
