package co.edu.unicundi.proyectoSpringPrueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IExamenService;

@RestController
@RequestMapping("/examen")
public class ExamenController {

	@Autowired
	private IExamenService service;

	@GetMapping("/traerExamenes/{pagina}/{registros}")
	public ResponseEntity<?> traerExamenes(@PathVariable Integer pagina, @PathVariable Integer registros)
			throws EmptyListException {
		Page<Examen> lista = service.traerPaginado(pagina, registros);
		return new ResponseEntity<Page<Examen>>(lista, HttpStatus.OK);
	}

	@PostMapping("/guardarExamen")
	public ResponseEntity<?> guardarExamen(@Valid @RequestBody Examen examen)
			throws ConflictException, ModelNotFoundException {
		service.guardar(examen);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@PutMapping("/editar")
	public ResponseEntity<?>editarExamen(@Valid @RequestBody Examen examen) throws ModelNotFoundException {
		service.editar(examen);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{idExamen}")
	public ResponseEntity<?> eliminarExamen(@PathVariable Integer idExamen) throws ModelNotFoundException {
		service.eliminar(idExamen);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
