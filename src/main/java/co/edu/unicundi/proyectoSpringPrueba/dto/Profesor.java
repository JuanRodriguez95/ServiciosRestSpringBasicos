package co.edu.unicundi.proyectoSpringPrueba.dto;

public class Profesor {

	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
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
