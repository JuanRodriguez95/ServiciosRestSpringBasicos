package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenDto;
import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenes;
import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;

import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.view.VistaMedicos;

public interface IConsultaExamenService extends ICrud<ConsultaExamen, Integer> {

	public void guardarNativo(ConsultaExamen consultaExamen);
	
	ConsultaExamenes listarPorIdCosnulta(Integer idConsulta,Boolean bandera)throws ModelNotFoundException,EmptyListException;
	
	Page<ConsultaExamen> listarPorIdCosnultaPaginado(Integer id, Integer page, Integer size);	
	
	public void recepcionDatos(ConsultaExamenDto datos)throws ModelNotFoundException;
	
	Page<VistaMedicos>traerVistaMedicos(Paginado page)throws EmptyListException;
	
	public void eliminarConsultaExamen(Integer idConsulta,Integer idExamen)throws ModelNotFoundException;

	public void editarConsultaExamen(ConsultaExamenDto datos)throws ModelNotFoundException;
	
}
