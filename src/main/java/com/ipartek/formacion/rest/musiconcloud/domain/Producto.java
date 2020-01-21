package com.ipartek.formacion.rest.musiconcloud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

//son anotaciones de JPA que es la interfaz de persistencia de java
@Entity
@Table(name="producto") //así este objeto se va a mapear contra la tabla producto
public class Producto {
	
	@Id //indicamos que es Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //campo autogenerado (autoincrement en bd)
	private int id;
	
	@Column(name="nombre", nullable=false, unique=true, length=50) //indica que es una columna básica
	private String nombre;
	
	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
	}
	
	public Producto(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	

}
