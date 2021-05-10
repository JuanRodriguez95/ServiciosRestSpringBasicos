package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
//import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IProfesorRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IProfesorService;

@Service
public class ProfesorServiceImp implements IProfesorService{

	@Autowired
	private IProfesorRepo profesorRepo;


	
	@Override
	public Profesor traerProfesorPorcedula(String cedula)throws ModelNotFoundException {
		Profesor profesor=null;
		//profesor=this.profesorRepo.findBycedula(cedula);
		//profesor=this.profesorRepo.buscarPorcedulaJPQL(cedula);
		profesor=this.profesorRepo.buscarPorcedulaSQL(cedula);
		//if(profesor==null){
		//	throw new ModelNotFoundException("La cedula solicitada no se encuentra registrada");
		//}
		return profesor;
	}

	@Override
	public Page<Profesor> traerProfesores(int pagina, int registros,String orden,String tipo) throws EmptyListException {
		Page<Profesor> listaOrdenada = null;
		System.out.println(pagina+"-"+registros+"-"+orden+"-"+tipo);
		if(this.profesorRepo.findAll().isEmpty())
			throw new EmptyListException("Lista Vacia.... no existen resgitros en la base de datos");
		if((validarOrden(orden))){
			if(tipo.equals("ASD")){
			System.out.println("entre a asd");
				listaOrdenada= this.profesorRepo.findAll(PageRequest.of(pagina, registros, Sort.Direction.ASC,orden));
			}

			if(tipo.equals("DSD"))	{
			    System.out.println("entre a dsd");
				listaOrdenada= this.profesorRepo.findAll(PageRequest.of(pagina, registros, Sort.Direction.DESC,orden));
			}
		}		
		return listaOrdenada;
	}

	@Override
	public Profesor guardarProfesor(Profesor profesor) throws ConflictException, ModelNotFoundException {
		Profesor profe = this.traerProfesorPorcedula(profesor.getCedula());
		if(profe != null){
			throw new ConflictException("La cedula ya se encuentra registrada en la base de datos");
		}			
		this.profesorRepo.save(profesor);
		return profesor;	
	}

	
	@Override
	public Profesor editarProfesor(Profesor profesor,Integer id)throws ConflictException,ModelNotFoundException {		
		Profesor profesoraux = this.traerPorId(id);	
			profesoraux.setNombre(profesor.getNombre());
			profesoraux.setApellido(profesor.getApellido());
			profesoraux.setCorreo(profesor.getCorreo());
			profesoraux.setEdad(profesor.getEdad());						
		return this.profesorRepo.save(profesoraux);
	}

	@Override
	public void eliminarProfesor(Integer id) throws ModelNotFoundException{		
		int idProfesor = this.profesorRepo.buscarPorId(id);
		if(idProfesor<=0)
			throw new ModelNotFoundException("el resgitro con el id solicitado no existe");
		this.profesorRepo.deleteById(id);
	}

	@Override
	public Profesor traerPorId(Integer id) throws ModelNotFoundException {				
		Profesor profesor = profesorRepo.findById(id).orElseThrow(()-> new ModelNotFoundException("el registro con el id solicitado no existe"));
		return profesor;
	}


	public boolean validarOrden(String orden){
		System.out.print("Entre a validar orden");
		if(((orden.equals("nombre"))||(orden.equals("apellido"))||(orden.equals("cedula"))||(orden.equals("edad")))){
			System.out.print("True");
			return true;
		}else{
			System.out.print("False");
			return false;
		}

	}
}
