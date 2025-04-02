package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.GradoInstRepository;
import com.unu.sistemadegestiondocumentaria.repository.PersonaRepository;
import java.util.List;

public class PersonaService {

	private static PersonaService INSTANCIA;

	private GradoInstRepository giRepository;
	private PersonaRepository personaRepository;

	private PersonaService() {
		giRepository = GradoInstRepository.instanciar();
		personaRepository = PersonaRepository.instanciar();
	}

	public static PersonaService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new PersonaService();
		}
		return INSTANCIA;
	}

	public void add(Persona p) {
		GradoInstruccion gi = giRepository.getById(p.getIdGradoInst());
		if (gi == null) {
			return;
		}
//		throw new ValidationException("El Grado de Instrucción de la Persona no puede estar vacío.");
		p.setGradoInstruccion(gi);

		personaRepository.add(p);
	}

	public void update(int id, Persona p) {
		Persona persona = personaRepository.getById(id);
		if (persona == null) {
			return;
		}

		GradoInstruccion gi = giRepository.getById(p.getIdGradoInst());
		if (gi == null) {
			return;
		}
//		throw new ValidationException("El Grado de Instrucción de la Persona no puede estar vacío.");
		p.setGradoInstruccion(gi);

		persona.setNombre(p.getNombre());
		persona.setApellidoPaterno(p.getApellidoPaterno());
		persona.setApellidoMaterno(p.getApellidoMaterno());
		persona.setGradoInstruccion(p.getGradoInstruccion());

		personaRepository.update(id, persona);
	}

	public void delete(int id) {
		personaRepository.delete(id);
	}

	public Persona getById(int id) {
		return personaRepository.getById(id);
	}
	
	public int getLastId() {
		return personaRepository.getLastId();
	}
	
	public Persona getLast() {
		return personaRepository.getLast();
	}
	
	public List<Persona> getAll(){
		return personaRepository.getAll();
	}

//	/*
//	 * Esto no deberia estar aqui, pues aqui solo deberia haber todo lo referente a persona
//	 * Pero no quiero instanciar otra service, que pregunte si esta vacio para insertar y recien traer
//	 * De aqui defrente nomas
//	 */
//	public List<GradoInstruccion> getAllGradosInstruccion() {
//		return giRepository.getAll();
//	}

}
