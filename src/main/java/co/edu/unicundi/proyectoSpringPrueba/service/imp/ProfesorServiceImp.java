package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.NotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IProfesorService;

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
	public Respuesta traerProfesorPorcedula(Double cedula)throws ModelNotFoundException {
		Respuesta respuesta=null;
			for (Profesor obj : lista) {
				if(obj.getCedula()==cedula) {
					respuesta = new Respuesta("Registro encontrado", obj,200);					
				}
			}
			if(cedula==0)
				throw new ModelNotFoundException("Error ----------- el objeto no se encontro");
		return respuesta;
	}

	@Override
	public Respuesta traerProfesores() {
		if(!this.lista.isEmpty()) {
			Respuesta respuesta = new Respuesta("Registro encontrado", this.lista,200);
			return respuesta;
		}else {
			Respuesta respuesta = new Respuesta("No hay resgistros en la lista",null,204);
			return respuesta;
		}		
	}

	@Override
	public Respuesta guardarProfesor(Profesor profesor)throws HttpMediaTypeNotSupportedException {
		Respuesta respuesta=null;
		
			for (Profesor profe : lista) {
				if(profe.getCedula()==profesor.getCedula()) {
					respuesta = new Respuesta("ya existe un registro con la cedula ingresada", null ,200);
					return respuesta;
				}
			}
			if(!this.lista.isEmpty()){
				profesor.setId(this.lista.get(this.lista.size()-1).getId()+1);
			}else{
				profesor.setId(1);
			}	
				this.lista.add(profesor);
			respuesta = new Respuesta("Profesor guardado", profesor.getId(),201);
		
		
		return respuesta;
	}

	
	@Override
	public Respuesta editarProfesor(Profesor profesor,Integer id) {
		boolean bandera=true;
		Respuesta respuesta=null;
		
			for (Profesor obj : lista) {
				if(obj.getCedula()==profesor.getCedula()){
					bandera=false;
				}
			}
			if(bandera){
				for (Profesor proOri : lista) {
					System.out.println(proOri.getId());
					System.out.println(id);
					if(proOri.getId()==id) {
						proOri.setNombre(profesor.getNombre());
						proOri.setApellido(profesor.getApellido());	
						proOri.setCedula(profesor.getCedula());
						respuesta = new Respuesta("Registro editado", proOri,200);
						return respuesta;
					}else{
						respuesta = new Respuesta("El id no existe", null,404);
					}
				}
			}else{
				respuesta = new Respuesta("la cedula ya esta registrada", null,409);
			}
				
		return respuesta;
	}

	@Override
	public Respuesta eliminarProfesor(Integer id) {
		
			int cont=0;
			Respuesta respuesta=null;
			boolean bandera=false;
				for (Profesor profesor : lista) {
					if(profesor.getId()==id) {
						cont=lista.indexOf(profesor);
						System.out.println(cont);
						bandera=true;
					}
				}
				if(bandera==false) {
					respuesta = new Respuesta("Id no encontrado", null ,404 );
				}else {
					this.lista.remove(cont);
					respuesta = new Respuesta("Registro eliminado", null ,204 );
				}
			return respuesta;
	}

}
