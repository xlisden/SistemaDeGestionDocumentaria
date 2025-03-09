package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.unu.sistemadegestiondocumentaria.config.HibernateConfig;
import com.unu.sistemadegestiondocumentaria.entity.Egresado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class ExpedienteService extends Repository<Expediente> {

    private EgresadoService egresadoService = new EgresadoService(Egresado.class);

    private HibernateConfig hc = new HibernateConfig();
    private EntityManager em;

    public ExpedienteService(Class<Expediente> type) {
        super(type);
    }

    public void add(Persona t) {
        Egresado eg = null;
        Expediente ex = null;
        try {
            egresadoService.add(t);
            eg = egresadoService.getLast();
            if (!egresadoService.getAll().isEmpty() && eg.getId() == getLast().getEgresado().getId()) {
                return;
            }

            ex = new Expediente(eg);
            ex.setNroExpediente(getNroExp());
            super.add(ex);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void update(int id, Persona p) {
        try {
            Expediente exp = getById(id);
            if (exp == null) {
                throw new ValidationException(Validation.showWarning("El Expediente no puede estar vacío."));
            }

            egresadoService.update(exp.getEgresado().getId(), p);
            //no hacemos update del nroExp porque se supone que va de acuerdo al egresado. Se supone que es unico, al igual que el egresado.
            //si eliminamos al egresado 3, esta bien que el sgte exp sea el 4, asi ya no haya un exp 3
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public List<Expediente> getAll() {
        return super.getAll();
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

    public Expediente getByEgresado2(int idEg) {
        Expediente t = null;
        em = hc.getEntityManager();
        t = em.createQuery("SELECT x FROM Expediente x WHERE x.idEgresado = :idEg", Expediente.class).setMaxResults(1).getSingleResult();
        hc.closeConnection();
        return t;
    }

    private Expediente getByEgresado(int idEg) {
        Expediente eg = null;
        super.getByQuery("SELECT x FROM Expediente x WHERE x.idEgresado = :idEg");
        return eg;
    }

    private int getNroExp() {
        int nroExp = 0;
        Expediente exp = null;
        try {
            exp = super.getLast();
            if (exp == null) {
                nroExp = 1;
            } else {
                nroExp = exp.getNroExpediente() + 1;
            }
        } catch (ValidationException e) {
            e.printMessage();
        }
        return nroExp;
    }

}
