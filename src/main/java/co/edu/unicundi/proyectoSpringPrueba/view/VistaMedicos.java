package co.edu.unicundi.proyectoSpringPrueba.view;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.google.gson.Gson;

@Entity
@Table(name = "vista_medicos")
@Immutable
public class VistaMedicos implements Serializable{

	@Id
	@Column(name = "id_consulta")
	private Integer idConsulta;
	
	@Column(name = "id_medico")
	private Integer idMedico;
	
	@Column(name = "nombre_medico")
	private String nombreMedico;
	
	@Column(name = "apellido_medico")
	private String apellidoMedico;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "examenes")
	private Integer examenes;
	
	@Column(name = "detalles")
	private Integer detalles;

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getApellidoMedico() {
		return apellidoMedico;
	}

	public void setApellidoMedico(String apellidoMedico) {
		this.apellidoMedico = apellidoMedico;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getExamenes() {
		return examenes;
	}

	public void setExamenes(Integer examenes) {
		this.examenes = examenes;
	}

	public Integer getDetalles() {
		return detalles;
	}

	public void setDetalles(Integer detalles) {
		this.detalles = detalles;
	}

	
}
