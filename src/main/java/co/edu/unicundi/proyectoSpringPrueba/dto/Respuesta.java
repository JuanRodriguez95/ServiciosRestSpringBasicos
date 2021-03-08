package co.edu.unicundi.proyectoSpringPrueba.dto;

public class Respuesta {

	private String respuesta;
	
	private Object registro;

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
