package com.formacion.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.dao.AlquilerDao;
import com.formacion.app.entity.Alquiler;
import com.formacion.app.entity.Cliente;
import com.formacion.app.entity.Coche;

@Service
public class AlquilerServiceImpl implements AlquilerService{

	@Autowired
	private AlquilerDao alquilerDao;
	
	@Override
	public List<Alquiler> findAll() {
		return (List<Alquiler>) alquilerDao.findAll();
	}

	@Override
	public Alquiler findById(Long id) {
		return  alquilerDao.findById(id).orElse(null);
	}

	@Override
	public Alquiler save(Alquiler alquiler) {
		return alquilerDao.save(alquiler);
	}

	@Override
	public void delete(Long id) {
		alquilerDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Coche> findAllCoches() {
		return alquilerDao.findAllCoches();
	}

	@Override
	public List<Cliente> findAllClientes() {
		return alquilerDao.findAllClientes();
	}

}
