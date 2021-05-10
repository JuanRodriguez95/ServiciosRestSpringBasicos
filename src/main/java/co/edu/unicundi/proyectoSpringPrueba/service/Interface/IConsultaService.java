package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.PageException;

public interface IConsultaService extends ICrud<Consulta,Integer>{

	public void guardarRelacion(Consulta consulta,Integer id)throws ModelNotFoundException;

	
	//public Page<Consulta> retornar(Paginado datos)throws EmptyListException,OrderNotFoundException;
}