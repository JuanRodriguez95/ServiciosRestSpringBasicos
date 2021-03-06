package co.edu.unicundi.proyectoSpringPrueba.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ExceptionResponse {
    
    @ApiModelProperty(position = 3,notes = "Fecha y Hora de el lanzamiento de la excepcion")
    private String timestamp;
	
    @ApiModelProperty(position = 0,notes = "Codigo de estado para la excepcion lanzada")
	private int status;
	
    @ApiModelProperty(position = 1,notes = "Nombre del error")
	private String error;
	
    @ApiModelProperty(position = 2,notes = "Mensaje especifico de error")
	private Object message;
	
    @ApiModelProperty(position = 4,notes = "Url en la que ocurrio el error especificado")
	private String path;

	public ExceptionResponse(int status, String error, Object message, String path) {
		super();
		this.timestamp = LocalDateTime.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
