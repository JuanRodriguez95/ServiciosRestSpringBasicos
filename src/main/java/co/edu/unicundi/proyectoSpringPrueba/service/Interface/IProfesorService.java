package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.data.domain.Page;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;

//import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;
//import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;


public interface IProfesorService {
	
	public Profesor traerProfesorPorcedula(String cedula)throws ModelNotFoundException;
	public Page<Profesor> traerProfesores(int pagina,int registros,String orden,String tipo)throws EmptyListException;
	public Profesor guardarProfesor(Profesor profesor)throws ConflictException,ModelNotFoundException ;
	public Profesor editarProfesor(Profesor profesor,Integer id) throws ConflictException,ModelNotFoundException ; 
	public void eliminarProfesor(Integer id)throws ModelNotFoundException;
	public Profesor traerPorId(Integer id) throws ModelNotFoundException;

}
