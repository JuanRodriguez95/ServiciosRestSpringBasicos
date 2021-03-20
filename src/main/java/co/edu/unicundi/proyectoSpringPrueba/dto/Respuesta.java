package co.edu.unicundi.proyectoSpringPrueba.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class Respuesta {

	@ApiModelProperty(position = 1,notes = "Mensaje de respuesta")
	@ApiParam(name = "Mensaje de respuesta")
	private String respuesta;
	
	@ApiModelProperty(position = 2,notes = "atributo de tipo Object que almacena el resgistro consultado")
	@ApiParam(name = "atributo de tipo Object que almacena el resgistro consultado")
	private Object registro;

	@ApiModelProperty(position = 0,notes = "Codigo de respuesta arrojado por el servicio")
	@ApiParam(name = "Codigo de respuesta arrojado por el servicio")
	private int codigo;

	public Respuesta(String respuesta, Object registro, int codigo) {
		super();
		this.respuesta = respuesta;
		this.registro = registro;
		this.codigo=codigo;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Object getRegistro() {
		return registro;
	}

	public void setRegistro(Object registro) {
		this.registro = registro;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
