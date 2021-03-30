package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;

import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;

import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IProfesorService;
import springfox.documentation.schema.Model;

@Service
public class ProfesorServiceImp implements IProfesorService{

	ArrayList<Profesor> lista = new ArrayList<Profesor>();
	ArrayList<Profesor> listaVacia = new ArrayList<Profesor>();

	public ProfesorServiceImp() {
		llenar();
	}
	
	public void llenar() {
		Profesor pv = new Profesor(0,"Lista vacia","Sin datos",1074188);
		Profesor p1 = new Profesor(1,"Pepe","Rodriguez",1074189);
		Profesor p2 = new Profesor(2,"Jose","Contreras",1074190);
		Profesor p3 = new Profesor(3,"Pablo","Mocho",1074191);
		this.lista.add(p1);
		this.lista.add(p2);
		this.lista.add(p3);
		this.listaVacia.add(pv);
	}
	
	@Override
	public Profesor traerProfesorPorcedula(Double cedula)throws ModelNotFoundException {
		Profesor profesor=null;
		boolean bandera=true;
			for (Profesor obj : lista) {
				if(obj.getCedula()==cedula) {
				    profesor = obj;	
					return obj;			
				}
			}	
			if(profesor==null){
				throw new ModelNotFoundException("El registro solicitado no se encuentra en la base de datos");
			}
		return profesor;
	}

	@Override
	public Respuesta traerProfesores() {
		//return this.lista;
		if(!this.lista.isEmpty()) {
			Respuesta respuesta = new Respuesta("Registro encontrado", this.lista,200);
			return respuesta;
		}else {
			Respuesta respuesta = new Respuesta("No hay resgistros en la lista",null,204);
			return respuesta;
		}		
	}

	@Override
	public Profesor guardarProfesor(Profesor profesor) throws ConflictException {
			for (Profesor profe : lista) {
				if(profe.getCedula()==profesor.getCedula()) {
					throw new ConflictException("la cedula ingresada ya se encuentra registrada en la base de datos");
				}
			}
			if(!this.lista.isEmpty()){
				profesor.setId(this.lista.get(this.lista.size()-1).getId()+1);
			}else{
				profesor.setId(1);
			}	
				this.lista.add(profesor);
		return profesor;
		
	}

	
	@Override
	public Profesor editarProfesor(Profesor profesor,Integer id)throws ConflictException,ModelNotFoundException {
		boolean bandera=true;
		Profesor profesorAux = null;
			for (Profesor obj : lista) {
				if(obj.getCedula()==profesor.getCedula()){
					throw new ConflictException("La cedula ingresada ya se encuentra registrada en la base datos");
				}
			}
			for(Profesor proOri : lista){
				if(proOri.getId()==id) {
					proOri.setNombre(profesor.getNombre());
					proOri.setApellido(profesor.getApellido());	
					proOri.setCedula(profesor.getCedula());
					profesorAux=proOri;		
					return profesorAux;		
				}
			}
			if(profesorAux==null)
				throw new ModelNotFoundException("El id ingresado no existe en la base de datos");			
		return profesorAux;
	}

	@Override
	public Profesor eliminarProfesor(Integer id) throws ModelNotFoundException{
		
			int cont=0;
			Respuesta respuesta=null;
			boolean bandera=false;
			Profesor profe = null;
				for (Profesor profesor : lista) {
					if(profesor.getId()==id) {
						cont=lista.indexOf(profesor);
						System.out.println(cont);
						bandera=true;
					}
				}
				if(bandera==false) {
					throw new ModelNotFoundException("el id no existe en la base de datos");
				}else {
					this.lista.remove(cont);
				}
			return profe;
	}

}
