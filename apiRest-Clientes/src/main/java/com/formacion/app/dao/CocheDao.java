package com.formacion.app.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.entity.Coche;


@Repository
public interface CocheDao extends CrudRepository<Coche, Long>{


}
