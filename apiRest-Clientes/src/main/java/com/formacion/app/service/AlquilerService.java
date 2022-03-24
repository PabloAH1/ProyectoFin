package com.formacion.app.service;

import java.util.List;

import com.formacion.app.entity.Alquiler;
import com.formacion.app.entity.Cliente;
import com.formacion.app.entity.Coche;


public interface AlquilerService {
	public List<Alquiler> findAll();
	
	public Alquiler findById(Long id);
	
	public Alquiler save(Alquiler alqulier);
	
	public void delete(Long id);
	
	public List<Coche> findAllCoches();
	
	public List<Cliente> findAllClientes();
}
