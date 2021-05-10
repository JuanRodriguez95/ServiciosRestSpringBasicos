package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;

public interface IMedicoService extends ICrud<Medico,Integer>{

    
    public Medico traerMedicoConsultas(Integer idMedico,Boolean consultas)throws ModelNotFoundException;

    Page<Medico> traerMedicosPaginadosOrdenados(Paginado datos)throws EmptyListException,OrderNotFoundException;

    Page<Medico> traerMedicoPorNombre(String nombre,Paginado datos)throws ModelNotFoundException,EmptyListException;

    Page<Medico> traerMedicoPorApellido(String apellido,Paginado datos)throws ModelNotFoundException,EmptyListException;

    Medico traerMedicoPorCorreo(String correo)throws ModelNotFoundException;

    Page<Medico> traerMedicoPorDetalleDireccion(String detalle,Paginado datos)throws ModelNotFoundException,EmptyListException;

    Page<Medico> traerMedicoPorPais(String pais,Paginado datos)throws OrderNotFoundException,EmptyListException;

    Page<Medico> traerMedicoPorCiudad(String ciudad,Paginado datos)throws OrderNotFoundException,EmptyListException;

    Page<Medico> traerMedicoPorBarrio(String barrio,Paginado datos)throws OrderNotFoundException,EmptyListException;
}
