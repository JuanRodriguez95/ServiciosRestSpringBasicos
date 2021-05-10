package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IDetalleConsultaService;

@RestController
@RequestMapping("/detalleconsulta")
public class DetalleConsultaController {

	@Autowired
	IDetalleConsultaService service;
/*
	@GetMapping("/retornar/{idDoctor}/{pagina}/{registros}/{orden}/{tipo}")
	public ResponseEntity<?> retornar(@PathVariable Integer idDoctor, @PathVariable int pagina,@PathVariable int registros, @PathVariable String orden, @PathVariable String tipo)throws ModelNotFoundException, EmptyListException, OrderNotFoundException {
			Page<DetalleConsulta> listaConsulta = service.retornarDetalles(idDoctor,pagina,registros,orden,tipo);
			return new ResponseEntity<Page<DetalleConsulta>>(listaConsulta, HttpStatus.OK);			
	}	
*/	
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
        DetalleConsulta consulta = service.traerPorId(id);
			return new ResponseEntity<DetalleConsulta>(consulta, HttpStatus.OK);			
	}	
	/*
	@GetMapping("/retornaPorIdConsulta/{nombre}")
	public ResponseEntity<?> retornarPorIdConsulta(@PathVariable String nombre) throws ModelNotFoundException  {
        Consulta consulta = service.retornarPorIdConsulta(nombre);
			return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);			
	}
*/
	@PostMapping("/guardar/{idMedico}")
	public ResponseEntity<?> guardar(@Valid @RequestBody DetalleConsulta consulta,@PathVariable Integer idMedico)
            throws ModelNotFoundException {
			service.guardarRelacion(consulta,idMedico);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody DetalleConsulta detalle) throws ModelNotFoundException {	
			service.editar(detalle);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> eliminar(@PathVariable int id) throws ModelNotFoundException {
	    service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	

}
