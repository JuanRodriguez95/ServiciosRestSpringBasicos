package co.edu.unicundi.proyectoSpringPrueba.dto;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class Profesor {

	@ApiModelProperty(position = 0,notes = "valor autoincremental que actua como llave primaria en la base de datos")
	@NotNull(message = "Id es obligatorio")
	private Integer id;
	
	@ApiModelProperty(position = 1,notes=" Nombre del profesor")
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "Nombre debe tener entre 3 y 15 caracteres")
	private String nombre;
	
	@ApiModelProperty(position = 2,notes="Apellido del profesor")
	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
	private String apellido;
	
	@ApiModelProperty(position = 3,notes="Numero de identificacion del profesor, este numero es unico para cada registro")
	@NotNull(message = "Cedula es obligatoria")
	@Min(value = 9999999)
	private double cedula;

	public Profesor() {
		
	}

	public Profesor(Integer id, String nombre, String apellido, double cedula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula=cedula;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public double getCedula() {
		return cedula;
	}

	public void setCedula(double cedula) {
		this.cedula = cedula;
	}
	
}
