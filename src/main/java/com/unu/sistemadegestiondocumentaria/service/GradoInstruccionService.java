package com.unu.sistemadegestiondocumentaria.service;

import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.GradoInstruccion;
import com.unu.sistemadegestiondocumentaria.repository.GradoInstRepository;

public class GradoInstruccionService {

	private static GradoInstruccionService INSTANCIA;

	private GradoInstRepository giRepository;

	private GradoInstruccionService() {
		giRepository = GradoInstRepository.instanciar();
		addData();
	}

	public static GradoInstruccionService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new GradoInstruccionService();
		}
		return INSTANCIA;
	}

	public void add(GradoInstruccion gi) {
		giRepository.add(gi);
	}

	public GradoInstruccion getById(int id) {
		return giRepository.getById(id);
	}
	
	public List<GradoInstruccion> getAll(){
		return giRepository.getAll();
	}
	
	private void addData() {
		if(getAll().isEmpty()) {
			add(new GradoInstruccion("Bach."));
			add(new GradoInstruccion("Ing."));
			add(new GradoInstruccion("Mg."));
			add(new GradoInstruccion("MSc."));
			add(new GradoInstruccion("Dr."));
		}
	}

}
