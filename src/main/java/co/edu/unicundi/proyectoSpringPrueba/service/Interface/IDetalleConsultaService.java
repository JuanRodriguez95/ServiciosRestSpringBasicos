package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;

public interface IDetalleConsultaService extends ICrud<DetalleConsulta,Integer>{
    
	public void guardarRelacion(DetalleConsulta consulta,Integer idDoctor) throws ModelNotFoundException;
	
	
}
