package co.edu.unicundi.proyectoSpringPrueba.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IProfesorService;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
	

	@Autowired
	IProfesorService profesorService;
	
	@GetMapping("/retornarPorId/{id}")
	public ResponseEntity<Respuesta> retornarProfesor(@PathVariable double id) {
		//return profesorService.traerProfesorPorId(id);
		Respuesta respuesta = profesorService.traerProfesorPorcedula(id);
		return new ResponseEntity<Respuesta>(respuesta,HttpStatus.resolve(respuesta.getCodigo()));
	}
	
	@GetMapping("/retornarLista")
	public ResponseEntity<Respuesta> retornarProfesores() {
		Respuesta respuesta = profesorService.traerProfesores();
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.resolve(respuesta.getCodigo()));
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Respuesta> guardarProfesor(@RequestBody Profesor profesor) {
		Respuesta respuesta = profesorService.guardarProfesor(profesor);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.resolve( respuesta.getCodigo()));
	}
	
	@PutMapping("editar/{id}")
	public ResponseEntity<Respuesta> editarProfesor(@PathVariable int id,@RequestBody Profesor profesor) {
		Respuesta respuesta = profesorService.editarProfesor(profesor, id);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.resolve(respuesta.getCodigo()));
	}
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Respuesta> elimianrProfesor(@PathVariable int id) {
		Respuesta respuesta = profesorService.eliminarProfesor(id);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.resolve(respuesta.getCodigo()));
	}

	
	
}
