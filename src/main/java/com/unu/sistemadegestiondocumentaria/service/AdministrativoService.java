package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.entity.Persona;
import com.unu.sistemadegestiondocumentaria.repository.AdministrativoRepository;
import com.unu.sistemadegestiondocumentaria.repository.GradoInstRepository;
import com.unu.sistemadegestiondocumentaria.repository.PersonaRepository;
import java.util.Collections;
import java.util.List;

public class AdministrativoService {

	private static AdministrativoService INSTANCIA;

	private AdministrativoRepository adRepository;
	private GradoInstRepository giRepository;
	private PersonaRepository personaRepository;

	private AdministrativoService() {
		adRepository = AdministrativoRepository.instanciar();
		giRepository = GradoInstRepository.instanciar();
		personaRepository = PersonaRepository.instanciar();
		addData();
	}

	public static AdministrativoService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new AdministrativoService();
		}
		return INSTANCIA;
	}

	public void add(Persona p) {
		GradoInstruccion gi = giRepository.getById(p.getIdGradoInst());
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

		adRepository.add(new Administrativo(p));
	}

	public void update(int id, Persona p) {
		Administrativo ad = getById(id);
		if (ad == null) {
			return;
		}

		int idPersona = ad.getPersona().getId();
		Persona persona = personaRepository.getById(idPersona);
		// se supone que existe
//		if (persona == null) {
//			return;
//		}

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

		personaRepository.update(idPersona, persona);
	}

	public void delete(int id) { // si elimino una persona, si se elimina admin. Pero no se elimina persona si elimino admin
		Administrativo ad = getById(id);
		if (ad == null) {
			return;
		}

		int idPersona = ad.getPersona().getId();
		
		personaRepository.delete(idPersona);
	}

	public Administrativo getById(int id) {
		return adRepository.getById(id);
	}

	public List<Administrativo> getAll() {
		return adRepository.getAll();
	}

	public List<Administrativo> getAllAdminOrdenAlfNombre() {
		return adRepository.getAllAdminOrdenAlfNombre();
	}
	
	// por si crean filtros para ordenar alfabeticamente, no estar llamando de nuevo a la database
	public List<Administrativo> getAllOrdenAlfNombre(List<Administrativo> lista) {
		if (lista != null) {
			Collections.sort(lista,
					(x, y) -> x.getPersona().getNombre().compareToIgnoreCase(y.getPersona().getNombre()));
		}
		return lista;
	}
	
	public List<Administrativo> getAllAdminOrdenAlfApPaterno() {
		return adRepository.getAllAdminOrdenAlfApPaterno();
	}

	// por si crean filtros para ordenar alfabeticamente, no estar llamando de nuevo a la database
	public List<Administrativo> getAllOrdenAlfApPaterno(List<Administrativo> lista) {
		if (lista != null) {
			Collections.sort(lista, (x, y) -> x.getPersona().getApellidoPaterno()
					.compareToIgnoreCase(y.getPersona().getApellidoPaterno()));
		}
		return lista;
	}
	
	/*
	 * Esto no deberia estar aqui, pues aqui solo deberia haber todo lo referente a
	 * persona Pero no quiero instanciar otro service, que pregunte si esta vacio
	 * para insertar y recien traer De aqui defrente nomas
	 */
	public List<GradoInstruccion> getAllGradosInstruccion() {
		return giRepository.getAll();
	}

	private void addData() {		
		if (adRepository.getAll().isEmpty()) {
			add(new Persona("Arturo", "Yupanqui", "Villanueva", 3));
			add(new Persona("David Abel", "Gonzalez", "Manrique De Lara", 5));
			add(new Persona("Clotilde", "Ríos", "Hidalgo De Cerna", 4));
			add(new Persona("Nilton Cesar", "Ayra", "Apac", 5));
//			add(new Persona("Oriana Olenka", "Montes", "Bellido", null));
			add(new Persona("Horacio", "Soriano", "Alava", 3));
			add(new Persona("Fernando", "Rafael", "Lean", 5));
			add(new Persona("Eleuterio", "Pérez", "Ságartegui", 3));
			add(new Persona("Walter G.", "Román", "Claros", 5));
			add(new Persona("Daniel", "Pérez", "Castañón", 3));
			add(new Persona("Joel Víctor", "Quispe", "Auccasi", 3));
			add(new Persona("Devyn Omar", "Donayre", "Hernández", 3));
			add(new Persona("Jorge Luis", "Hilario", "Rivas", 3));
			add(new Persona("Freddy Elar", "Ferrari", "Fernandez", 3));
			add(new Persona("Cesar Augusto", "Agurto", "Cherre", 3));
			add(new Persona("Ronald", "Ulloa", "Gálvez", 3));
			add(new Persona("Euclides", "Panduro", "Padilla", 3));
			add(new Persona("Oscar Amado", "Ruiz", "Torres", 4));
		}

	}

}
