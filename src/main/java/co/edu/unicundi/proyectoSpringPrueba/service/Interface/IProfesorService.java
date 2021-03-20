package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.NotSupportedException;

public interface IProfesorService {
	
	public Respuesta traerProfesorPorcedula(Double cedula)throws ModelNotFoundException;
	public Respuesta traerProfesores();
	public Respuesta guardarProfesor(Profesor profesor)throws HttpMediaTypeNotSupportedException;
	public Respuesta editarProfesor(Profesor profesor,Integer id);
	public Respuesta eliminarProfesor(Integer id);
	

}
