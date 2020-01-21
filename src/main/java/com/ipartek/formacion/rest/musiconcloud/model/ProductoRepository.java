package com.ipartek.formacion.rest.musiconcloud.model;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.rest.musiconcloud.domain.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
	
}
