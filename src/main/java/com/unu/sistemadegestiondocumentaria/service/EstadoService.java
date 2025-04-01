package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.repository.EstadoRepository;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;

public class EstadoService {

    private static EstadoService INSTANCIA;
    
    private EstadoRepository estRepository;

    private EstadoService() {
    	estRepository = EstadoRepository.instanciar();
    	addData();
    }

    public static EstadoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new EstadoService();
        }
        return INSTANCIA;
    }

    public void add(Estado est) {
        estRepository.add(est);
    }

    public Estado getById(int id) {
        return estRepository.getById(id);
    }
    
    public Estado getByNombre(String nombre) {
    	Estado est = estRepository.getByNombre(nombre);
    	
    	if(est == null) {
    		throw new ValidationException("No se encontró un estado con el nombre '" + nombre + "'.");
    	}
    	return est;
    }

    public void addData(){
		if (estRepository.getAll().isEmpty()) {
			add(new Estado("PENDIENTE"));
			add(new Estado("ENTREGADO"));
		}
    }
}
