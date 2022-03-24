package com.formacion.app.service;

import java.util.List;

import com.formacion.app.entity.Coche;



public interface CocheService {

	public List<Coche> findAll();
	
	public Coche findById(Long id);
	
	public Coche save(Coche cliente);
	
	public void delete(Long id);
	

	
}
