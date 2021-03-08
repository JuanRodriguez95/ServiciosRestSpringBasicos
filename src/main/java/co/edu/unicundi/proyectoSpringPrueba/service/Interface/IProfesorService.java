package co.edu.unicundi.proyectoSpringPrueba.service.Interface;




import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;

public interface IProfesorService {
	
	public Respuesta traerProfesorPorcedula(Double cedula);
	public Respuesta traerProfesores();
	public Respuesta guardarProfesor(Profesor profesor);
	public Respuesta editarProfesor(Profesor profesor,Integer id);
	public Respuesta eliminarProfesor(Integer id);
	

}
