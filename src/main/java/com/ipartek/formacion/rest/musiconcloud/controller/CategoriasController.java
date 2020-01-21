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

import com.ipartek.formacion.rest.musiconcloud.domain.Categoria;
import com.ipartek.formacion.rest.musiconcloud.domain.ReponseMensaje;
import com.ipartek.formacion.rest.musiconcloud.model.CategoriaRepository;


@RestController
@RequestMapping( value = {"/categoria/"})
public class CategoriasController {
	
	@Autowired                                // nuestro antiguo .getInstance()  
	CategoriaRepository categoriaRepository;  // nuestro antiguo DAO
	

	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Object> listar(){
		
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			
			ArrayList<Categoria> lista = (ArrayList<Categoria>) categoriaRepository.findAll();
			
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
	
	
	
	@RequestMapping(value = { "{id}" }, method = RequestMethod.GET)
	public ResponseEntity<Object> detalle(@PathVariable int id){
	
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			
			Optional<Categoria> cat = categoriaRepository.findById(id);
			if ( cat.isPresent() ) {
				response = new ResponseEntity<Object>(cat, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
			//response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return response;
		
	}
	
	
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Object> crear(@RequestBody Categoria cat){
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			
			categoriaRepository.save(cat);
			response = new ResponseEntity<Object>(cat, HttpStatus.CREATED);
			
		}catch (ConstraintViolationException e) {
			ReponseMensaje responseBody = new ReponseMensaje("Longitud Nombre min 3 max 50");
			response = new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST);
			
		}catch (Exception e) {
			e.printStackTrace();
			ReponseMensaje responseBody = new ReponseMensaje("Existe el nombre");
			response = new ResponseEntity<Object>(responseBody, HttpStatus.CONFLICT);
		}
		return response;
	}
	
	
}
