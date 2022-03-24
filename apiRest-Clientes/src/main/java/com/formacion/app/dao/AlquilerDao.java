package com.formacion.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.entity.Alquiler;
import com.formacion.app.entity.Cliente;
import com.formacion.app.entity.Coche;

@Repository
public interface AlquilerDao extends CrudRepository<Alquiler, Long>{
	@Query("from Coche")
	public List<Coche> findAllCoches();
	
	@Query("from Cliente")
	public List<Cliente> findAllClientes();
}
