package co.edu.unicundi.proyectoSpringPrueba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenDto;
import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenes;
import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.PageException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IConsultaService;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.ConsultaExamenServiceImpl;
import co.edu.unicundi.proyectoSpringPrueba.view.VistaMedicos;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@Autowired
	private ConsultaExamenServiceImpl serviceCE;
	/*
	@GetMapping("/retornar/{pagina}/{numeroRegistros}/{orden}/{tipo}")
	public ResponseEntity<?> retornar(@RequestBody Paginado datos)throws ModelNotFoundException, EmptyListException, PageException, OrderNotFoundException {
			Page<Consulta> listaConsulta = service.retornar(datos);
			return new ResponseEntity<Page<Consulta>>(listaConsulta, HttpStatus.OK);			
	}	
	
	@GetMapping("/retornarDetalle/{idMedico}")
	public ResponseEntity<?> retornarDetalle(@PathVariable int idMedico)
			throws ModelNotFoundException, EmptyListException, PageException {
			List<DetalleConsulta> listaDetalleConsulta = service.traerPorId(idMedico);
			return new ResponseEntity<List<DetalleConsulta>>(listaDetalleConsulta, HttpStatus.OK);			
	}	
*/
/*
	@GetMapping("/retornarConsultaPorNombreID/{nombre}/{idMedico}")
	public ResponseEntity<?> retornarConsultaNombreId(@PathVariable String nombre,@PathVariable int idMedico)throws ModelNotFoundException, EmptyListException, PageException {
			Consulta consulta = service.retornarPorNombreID(nombre, idMedico);
			return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);			
	}

	*/
	@GetMapping("/retornaPorId/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable int id) throws ModelNotFoundException  {
			Consulta consulta = service.traerPorId(id);
			return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);			
	}	
	
	@PostMapping("/guardar/{idMedico}")
	public ResponseEntity<?> guardar(@Valid @RequestBody Consulta consulta, @PathVariable Integer idMedico)
			throws ModelNotFoundException {
			service.guardarRelacion(consulta, idMedico);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@Valid @RequestBody Consulta consulta) throws ModelNotFoundException {	
			service.editar(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminar/{id}") 
	public ResponseEntity<Object> elimianr(@PathVariable int id) throws ModelNotFoundException {
		service.eliminar(id);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	


	//------------------------------------------------------------------------------------------------------------------

	@GetMapping("/vistaMedicos")
	public ResponseEntity<?> listarVista(@RequestBody Paginado datos)
			throws ModelNotFoundException {
			Page<VistaMedicos> consultas = serviceCE.traerVistaMedicos(datos);
			return new ResponseEntity<Page<VistaMedicos>>(consultas, HttpStatus.OK);				
	}
	
	
	@GetMapping("/listarConsulta/{id}/{bandera}")
	public ResponseEntity<?> listarConsulta(@PathVariable Integer id,@PathVariable Boolean bandera)
			throws ModelNotFoundException, EmptyListException {
			ConsultaExamenes consulta = serviceCE.listarPorIdCosnulta(id,bandera);
			return new ResponseEntity<ConsultaExamenes>(consulta, HttpStatus.CREATED);				
	}

	@GetMapping("/listarCP/{pagina}/{registros}")
	public ResponseEntity<?> listarConsulta(@PathVariable Integer pagina,@PathVariable Integer registros)
			throws ModelNotFoundException, EmptyListException {
			Page<ConsultaExamen> consulta = serviceCE.traerPaginado(pagina, registros);
			return new ResponseEntity<Page<ConsultaExamen>>(consulta, HttpStatus.CREATED);				
	}
	
	
	@PostMapping("/guardarCE")
	public ResponseEntity<?> guardarCE(@Valid @RequestBody ConsultaExamenDto consulta)
			throws ModelNotFoundException {
			serviceCE.recepcionDatos(consulta);
			return new ResponseEntity<Object>("", HttpStatus.CREATED);				
	}
	
	@PutMapping("/editarCE")
	public ResponseEntity<?> editarCE(@Valid @RequestBody ConsultaExamenDto consulta) throws ModelNotFoundException {	
			serviceCE.editarConsultaExamen(consulta);
			return new ResponseEntity<Object>("", HttpStatus.OK);				
	}	
	
	@DeleteMapping("eliminarCE/{idExamen}/{idConsulta}") 
	public ResponseEntity<Object> elimianrCE(@PathVariable Integer idExamen,@PathVariable Integer idConsulta) throws ModelNotFoundException {
		serviceCE.eliminarConsultaExamen(idConsulta, idExamen);
		return new ResponseEntity<Object>("", HttpStatus.NO_CONTENT);
	}	


}
