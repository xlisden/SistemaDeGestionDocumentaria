package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.ExpedienteRepository;
import com.unu.sistemadegestiondocumentaria.repository.GradoInstRepository;
import com.unu.sistemadegestiondocumentaria.repository.PersonaRepository;
import java.util.Collections;
import java.util.List;

public class ExpedienteService {

	private static ExpedienteService INSTANCIA;
	private ExpedienteRepository expRepository;
	private GradoInstRepository giRepository;
	private PersonaRepository personaRepository;

	private ExpedienteService() {
		expRepository = ExpedienteRepository.instanciar();
		giRepository = GradoInstRepository.instanciar();
		personaRepository = PersonaRepository.instanciar();
	}

	public static ExpedienteService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new ExpedienteService();
		}
		return INSTANCIA;
	}

	public void add(Persona p) {
		GradoInstruccion gi = giRepository.getById(p.getIdGradoInst()); // es que siempre seran bachilleres (eso creo)
		if (gi == null) {
			return;
		}
//			throw new ValidationException("El Grado de Instrucción de la Persona no puede estar vacío.");
		p.setGradoInstruccion(gi);

		personaRepository.add(p);

		int idPersona = personaRepository.getLastId();
//            if (!getAll().isEmpty() && idPersona == getLast().getPersona().getId()) {
//                return;
//            }
		p.setId(idPersona);

		Expediente exp = new Expediente(p);
		exp.setNroExpediente(getNroExp());
		expRepository.add(exp);
	}

	public void update(int id, Persona p) {
		Expediente exp = getById(id);
		if (exp == null) {
			return;
		}
		
		int idPersona = exp.getPersona().getId();
		Persona persona = personaRepository.getById(idPersona);
		// se supone que existe
//		if (persona == null) {
//			return;
//		}

		GradoInstruccion gi = giRepository.getById(p.getIdGradoInst()); // es que siempre seran bachilleres (eso creo)
		if (gi == null) {
			return;
		}
//		throw new ValidationException("El Grado de Instrucción de la Persona no puede estar vacío.");
		p.setGradoInstruccion(gi);

		persona.setNombre(p.getNombre());
		persona.setApellidoPaterno(p.getApellidoPaterno());
		persona.setApellidoMaterno(p.getApellidoMaterno());
		persona.setGradoInstruccion(p.getGradoInstruccion());

		personaRepository.update(idPersona, persona);
	}

	public void delete(int id) { // si elimino una persona, si se elimina exp. Pero no se elimina persona si elimino exp
		Expediente exp = getById(id);
		if (exp == null) {
			return;
		}
		getById(id);
		int idPersona = exp.getPersona().getId();
		
		personaRepository.delete(idPersona);
	}

	public Expediente getById(int id) {
		return expRepository.getById(id);
	}
	
	public List<Expediente> getAll() {
		return expRepository.getAll();
	}

	public List<Expediente> getAllExpOrdenAlfNombre() {
		List<Expediente> lista = expRepository.getAll();
		if (lista != null) {
			Collections.sort(lista,
					(x, y) -> x.getPersona().getNombre().compareToIgnoreCase(y.getPersona().getNombre()));
		}
		return lista;
	}

	public List<Expediente> getAllExpOrdenAlfApPaterno() {
		List<Expediente> lista = expRepository.getAll();
		if (lista != null) {
			Collections.sort(lista, (x, y) -> x.getPersona().getApellidoPaterno().compareToIgnoreCase(y.getPersona().getApellidoPaterno()));
		}
		return lista;
	}	
	
	private int getNroExp() {
		return (expRepository.getAll().isEmpty()) ? 1 : expRepository.getLastId() + 1;
	}

	/*
	 * Esto no deberia estar aqui, pues aqui solo deberia haber todo lo referente a
	 * persona Pero no quiero instanciar otro service, que pregunte si esta vacio
	 * para insertar y recien traer De aqui defrente nomas
	 */
	public List<GradoInstruccion> getAllGradosInstruccion() {
		return giRepository.getAll();
	}
	
}
