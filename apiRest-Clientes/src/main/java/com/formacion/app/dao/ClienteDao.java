package com.formacion.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.entity.Cliente;

@Repository
public interface ClienteDao extends CrudRepository<Cliente, Long>{


}
