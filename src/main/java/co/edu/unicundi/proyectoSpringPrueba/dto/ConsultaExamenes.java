package co.edu.unicundi.proyectoSpringPrueba.dto;

import java.util.List;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;

public class ConsultaExamenes {

	private Consulta consulta;
	
	private List<Examenes> listaExamenes;

	public ConsultaExamenes() {
		
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Examenes> getListaExamenes() {
		return listaExamenes;
	}

	public void setListaExamenes(List<Examenes> listaExamenes) {
		this.listaExamenes = listaExamenes;
	}

	
	
	
	
}
