package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;
//import co.edu.unicundi.proyectoSpringPrueba.dto.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;
import co.edu.unicundi.proyectoSpringPrueba.dto.Respuesta;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotAcceptableException;
//import co.edu.unicundi.proyectoSpringPrueba.exception.HttpMediaTypeNotSupportedException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IProfesorService;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

	/**
	 *
	 */
	@Autowired
	IProfesorService profesorService;

	@ApiOperation(value = "Retorna el registro de un profesor", notes = "Este servicio retorna el registro de un profesor utilizando su cedula como parametro de busqueda", response = Profesor.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "solicitud exitosa", response = Profesor.class),
			@ApiResponse(code = 400, message = "el servidor no pudo interpretar la solicitud", response = ExceptionResponse.class),
			@ApiResponse(code = 401, message = "Se requiere autenticacion para obtener una respuesta", response = ExceptionResponse.class),
			@ApiResponse(code = 403, message = "el cliente no posee los permisos para acceder al contenido", response = ExceptionResponse.class),
			@ApiResponse(code = 404, message = "el registro no fue encontrado", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class),

	})
	@GetMapping("/retornarPorId/{cedula}")

	public ResponseEntity<?> retornarProfesor(
			@ApiParam(name = "cedula", type = "String", value = "Cedula del profesor que se desea consultar", example = "123456", required = true) @PathVariable String cedula)
			throws ModelNotFoundException {
		Profesor respuesta = profesorService.traerProfesorPorcedula(cedula);
		return new ResponseEntity<Profesor>(respuesta, HttpStatus.OK);
	}
	// ---------------------------------------------------------------------------------------------------------------------------------

	@ApiOperation(value = "Retorna una lista de los profesores", notes = "Este servicio retorna una lista que contiene todos los registros de los profesores que actualmente"
			+ " estan almacenados en la base de datos ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "solicitud exitosa", response = Profesor.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "no se encuentran registros que mostrar"),
			@ApiResponse(code = 400, message = "el servidor no pudo interpretar la solicitud", response = ExceptionResponse.class),
			@ApiResponse(code = 401, message = "Se requiere autenticacion para obtener una respuesta", response = ExceptionResponse.class),
			@ApiResponse(code = 403, message = "el cliente no posee los permisos para acceder al contenido", response = ExceptionResponse.class),
			@ApiResponse(code = 404, message = "Recurso no encontrado", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class) })

	@GetMapping("/retornarLista/{pagina}/{registros}/{orden}/{tipo}")
	public ResponseEntity<?> retornarProfesores(@PathVariable int pagina,@PathVariable int registros,@PathVariable String orden,@PathVariable String tipo) throws EmptyListException {
		return new ResponseEntity<Page<Profesor>>(profesorService.traerProfesores(pagina,registros,orden,tipo),
				HttpStatus.OK);
	}
	// ------------------------------------------------------------------------------------------------------------------

	@ApiOperation(value = "Almacena el registro de un profesor", notes = "Este servicio almacena el resgitro de un profesor recibido en el cuerpo de la peticion "

	)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Registro creado exitosamente", response = Profesor.class),
			@ApiResponse(code = 409, message = "Conflicto en la peticion - (La cedula ingresada ya esta registrada)", response = ExceptionResponse.class),
			@ApiResponse(code = 404, message = "Recurso no encontrado", response = ExceptionResponse.class),
			@ApiResponse(code = 401, message = "Se requiere autenticacion para obtener una respuesta", response = ExceptionResponse.class),
			@ApiResponse(code = 403, message = "el cliente no posee los permisos para acceder al contenido", response = ExceptionResponse.class),
			@ApiResponse(code = 400, message = "el servidor no pudo interpretar la solicitud", response = ExceptionResponse.class),
			@ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class) })

	@PostMapping("/guardar")
	public ResponseEntity<?> guardarProfesor(
			@ApiParam(name = "profesor", type = "Profesor.class", value = "atributos que contiene el objeto Profesor en formato JSON", required = true) @Valid @RequestBody Profesor profesor)
			throws ConflictException, ModelNotFoundException {
		//Respuesta respuesta = profesorService.guardarProfesor(profesor);
		return new ResponseEntity<Profesor>(profesorService.guardarProfesor(profesor), HttpStatus.CREATED);
	}

//------------------------------------------------------------------------------------------------------------------
	
	@ApiOperation(value = "Edita el registro de un profesor",
					notes = "Este servicio permite editar el registro de un profesor guardado en la base de datos",
					response = Profesor.class
				)
	@ApiResponses(value = {
		@ApiResponse(code= 200, message = "Registro editado exitosamente", response = Profesor.class),
		@ApiResponse(code= 409, message = "Conflicto en la peticion - (La cedula ingresada ya esta registrada)",response = ExceptionResponse.class),
		@ApiResponse(code= 404, message = "El id del registro que desea editar no se encuentra en la base de datos",response = ExceptionResponse.class),
		@ApiResponse(code= 401, message = "Se requiere autenticacion para obtener una respuesta", response = ExceptionResponse.class),
		@ApiResponse(code= 403, message = "el cliente no posee los permisos para acceder al contenido", response = ExceptionResponse.class),
		@ApiResponse(code= 400, message = "el servidor no pudo interpretar la solicitud", response = ExceptionResponse.class),
		@ApiResponse(code= 500, message = "Error en el servidor",response = ExceptionResponse.class)
		})

	@PutMapping("editar/{id}")
	public ResponseEntity<?> editarProfesor(
					@ApiParam(  name = "id",
								type = "Integer.class",
							 	value = "Id del registro en la base de datos",
								required = true)
				@PathVariable int id,

					@ApiParam(  name = "profesor",
								type = "Profesor.class",
							 	value = "atributos modificados que conforman el objeto de tipo Profesor en formato JSON",
								required = true)
				@Valid @RequestBody Profesor profesor) throws ConflictException, ModelNotFoundException {
		//Respuesta respuesta = profesorService.editarProfesor(profesor, id);
		return new ResponseEntity<Profesor>((Profesor)profesorService.editarProfesor(profesor, id),HttpStatus.OK );
	}


//------------------------------------------------------------------------------------------------------------------
 
	@ApiOperation(value = "Elimina el registro de un profesor",
					notes = "Este servicio permite eliminar el registro de un profesor guardado en la base de datos"
					
					)
	@ApiResponses(value = {
		@ApiResponse(code= 204, message = "Registro eliminado exitosamente"),
		@ApiResponse(code= 404, message = "El id del registro que desea eliminar no se encuentra en la base de datos",response = ExceptionResponse.class),
		@ApiResponse(code= 400, message = "el servidor no pudo interpretar la solicitud", response = ExceptionResponse.class),
		@ApiResponse(code= 401, message = "Se requiere autenticacion para obtener una respuesta", response = ExceptionResponse.class),
		@ApiResponse(code= 403, message = "el cliente no posee los permisos para acceder al contenido", response = ExceptionResponse.class),
		@ApiResponse(code= 500, message = "Error en el servidor",response = ExceptionResponse.class)
		})

	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<?> elimianrProfesor(
					@ApiParam(  name = "id",
								type = "Integer",
							 	value = "Id del registro en la base de datos",								 
								required = true) 
								@PathVariable int id) throws ModelNotFoundException {
		profesorService.eliminarProfesor(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	
}
