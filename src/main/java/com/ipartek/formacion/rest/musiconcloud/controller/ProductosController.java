package com.ipartek.formacion.rest.musiconcloud.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.rest.musiconcloud.domain.Producto;
import com.ipartek.formacion.rest.musiconcloud.domain.ReponseMensaje;
import com.ipartek.formacion.rest.musiconcloud.model.ProductoRepository;


@RestController
@RequestMapping( value = {"/producto/"})
public class ProductosController {
							 // @Autowired es para hacer una inyección de dependencia, DI (una variable se usa y se instancia ella sola: no hace falta hacer un new, se hace automáticamente)
	@Autowired               // nuestro antiguo .getInstance()  
	ProductoRepository dao;  // nuestro antiguo DAO
	

	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Object> listar(){
		
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			
			ArrayList<Producto> lista = (ArrayList<Producto>) dao.findAll(); //nos busca todos
			
			if ( !lista.isEmpty() ) {			
				response = new ResponseEntity<Object>(lista, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
			//response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return response;
	}
	
	
	
	
	
	
}
