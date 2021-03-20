package co.edu.unicundi.proyectoSpringPrueba.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class ExceptionResponse {
    
    @ApiModelProperty(position = 3,notes = "Fecha y Hora de el lanzamiento de la excepcion")
    private String timestamp;
	
    @ApiModelProperty(position = 0,notes = "Codigo de estado para la excepcion lanzada")
	private String status;
	
    @ApiModelProperty(position = 1,notes = "Nombre del error")
	private String error;
	
    @ApiModelProperty(position = 2,notes = "Mensaje especifico de error")
	private String message;
	
    @ApiModelProperty(position = 4,notes = "Url en la que ocurrio el error especificado")
	private String path;

	public ExceptionResponse(String status, String error, String message, String path) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
