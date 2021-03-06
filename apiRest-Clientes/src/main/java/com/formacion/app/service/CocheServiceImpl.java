package com.formacion.app.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.dao.CocheDao;
import com.formacion.app.entity.Coche;

@Service
public class CocheServiceImpl implements CocheService{

	@Autowired
	private CocheDao cocheDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Coche> findAll() {
		return (List<Coche>) cocheDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Coche findById(Long id) {
		return  cocheDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Coche save(Coche cliente) {
		return cocheDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cocheDao.deleteById(id);
	}



}
