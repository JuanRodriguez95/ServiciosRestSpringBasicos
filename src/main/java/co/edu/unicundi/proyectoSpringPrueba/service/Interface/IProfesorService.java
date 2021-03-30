package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;
//import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;


public interface IProfesorService {
	
	public Profesor traerProfesorPorcedula(Double cedula)throws ModelNotFoundException;
	public Respuesta traerProfesores();
	public Profesor guardarProfesor(Profesor profesor)throws ConflictException ;
	public Profesor editarProfesor(Profesor profesor,Integer id) throws ConflictException,ModelNotFoundException ; 
	public Profesor eliminarProfesor(Integer id)throws ModelNotFoundException;
	

}
