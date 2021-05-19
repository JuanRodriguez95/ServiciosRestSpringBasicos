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

import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.imp.MedicoServiceImp;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoServiceImp service;

    @GetMapping("/traerPorId/{id}/{consultas}")
    public ResponseEntity<?> retornar(@PathVariable Integer id, @PathVariable Boolean consultas)
            throws ModelNotFoundException {
        Medico medico = service.traerMedicoConsultas(id, consultas);
        return new ResponseEntity<Medico>(medico, HttpStatus.OK);
    }

    @GetMapping("/traerMedicosPaginados")
    public ResponseEntity<?> traerMedicosPaginado(@Valid @RequestBody Paginado datos)
            throws EmptyListException, OrderNotFoundException {
        Page<Medico> lista = service.traerMedicosPaginadosOrdenados(datos);
        return new ResponseEntity<Page<Medico>>(lista, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@Valid @RequestBody Medico medico) throws ConflictException {
        service.guardar(medico);
        return new ResponseEntity<Medico>(medico,HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editar(@Valid @RequestBody Medico medico) throws ModelNotFoundException {
        service.editar(medico);
        return new ResponseEntity<Medico>(medico,HttpStatus.OK);

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws ModelNotFoundException {
        service.eliminar(id);
        return new ResponseEntity<Medico>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/traerMedicoPorNombre/{nombre}")
    public ResponseEntity<?> traerMedicoPorNombre(@PathVariable String nombre,@RequestBody Paginado datos)throws ModelNotFoundException,
            EmptyListException {
        Page<Medico> medico = service.traerMedicoPorNombre(nombre,datos);
        return new ResponseEntity<Page<Medico>>(medico,HttpStatus.OK);
    }

    @GetMapping("/traerMedicoPorApellido/{apellido}")
    public ResponseEntity<?> traerMedicosPorApellido(@PathVariable String apellido,@RequestBody Paginado datos)
            throws ModelNotFoundException, EmptyListException {
        Page<Medico> medico = service.traerMedicoPorApellido(apellido,datos);
        return new ResponseEntity<Page<Medico>>(medico,HttpStatus.OK);
    }

    @GetMapping("/traerMedicoPorCorreo/{correo}")
    public ResponseEntity<?> traerMedicoPorCorreo(@PathVariable String correo)
            throws ModelNotFoundException {
        Medico medico = service.traerMedicoPorCorreo(correo);
        return new ResponseEntity<Medico>(medico,HttpStatus.OK);
    }

    @GetMapping("/traerMedicoPorDetalle/{detalle}")
    public ResponseEntity<?> traerMedicoPorDetalle(@PathVariable String detalle,@RequestBody Paginado datos)
            throws ModelNotFoundException, EmptyListException {
        Page<Medico> medico = service.traerMedicoPorDetalleDireccion(detalle,datos);
        return new ResponseEntity<Page<Medico>>(medico,HttpStatus.OK);
    }

    @GetMapping("/traerMedicoPorCiudad/{ciudad}")
    public ResponseEntity<?> traerMedicoPorCiudad(@PathVariable String ciudad,@Valid @RequestBody Paginado datos)
            throws EmptyListException, OrderNotFoundException {
        Page<Medico> lista = service.traerMedicoPorCiudad(ciudad, datos);
        return new ResponseEntity<Page<Medico>>(lista,HttpStatus.OK);
    }
    @GetMapping("/traerMedicoPorBarrio/{barrio}")
    public ResponseEntity<?> traerMedicosPorBarrio(@PathVariable String barrio,@Valid @RequestBody Paginado datos)
            throws EmptyListException, OrderNotFoundException {
        Page<Medico> lista = service.traerMedicoPorBarrio(barrio, datos);
        return new ResponseEntity<Page<Medico>>(lista,HttpStatus.OK);
    }
    @GetMapping("/traerMedicoPorPais/{pais}")
    public ResponseEntity<?> traerMedicosPorPais(@PathVariable String pais,@Valid @RequestBody Paginado datos)
            throws EmptyListException, OrderNotFoundException {
        Page<Medico> lista = service.traerMedicoPorPais(pais, datos);
        return new ResponseEntity<Page<Medico>>(lista,HttpStatus.OK);
    }

    @GetMapping("/pruebaJenkins/{nombre}")
    public ResponseEntity<?> pruebaJenkins(@PathVariable String nombre,@PathVariable Integer edad,@PathVariable String ciudad)
            throws EmptyListException, OrderNotFoundException {
        String saludo = "HOLA "+ nombre+" Bienvenido; "+"Tienes "+ edad + " años ; y vives en "+ciudad+" ";
        return new ResponseEntity<String>(saludo,HttpStatus.OK);
    }

}
