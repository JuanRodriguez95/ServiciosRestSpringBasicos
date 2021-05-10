package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenDto;
import co.edu.unicundi.proyectoSpringPrueba.dto.ConsultaExamenes;
import co.edu.unicundi.proyectoSpringPrueba.dto.Examenes;
import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IVistaMedicosRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IConsultaExamenService;
import co.edu.unicundi.proyectoSpringPrueba.view.VistaMedicos;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
	
	@Autowired
	private IConsultaExamenRepo repo;

	@Autowired
	private IConsultaRepo repoConsulta;

	@Autowired
	private IExamenRepo repoExamen;
	
	@Autowired
	private IVistaMedicosRepo repoVista;

	@Override
	public void guardar(ConsultaExamen consultaEx) throws ModelNotFoundException{
		repo.save(consultaEx);
	}

	@Override
	public void editar(ConsultaExamen consultaEx) throws ModelNotFoundException {
		repo.editar(consultaEx.getInfoAdicional(), consultaEx.getExamen().getId(), consultaEx.getConsulta().getId());
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		
	}

	@Override
	public void guardarNativo(ConsultaExamen consultaExamen) {
		//repo.guardar(consultaExamen.getConsulta().getId(), consultaExamen.getExamen().getId(), consultaExamen.getInfoAdicional());
	}

	@Override
	public ConsultaExamenes listarPorIdCosnulta(Integer idConsulta,Boolean bandera)throws ModelNotFoundException,EmptyListException {
		if(repoConsulta.buscarPorId(idConsulta)<=0)
			throw new ModelNotFoundException("no existen registros con el id solicitado");
		ConsultaExamenes consultas = new ConsultaExamenes();
		List<ConsultaExamen> listaconsulta = repo.listarPorIdCosnulta(idConsulta);
		if(listaconsulta.isEmpty())
			throw new EmptyListException("no hay registros asociados a la consulta");
		consultas.setConsulta(listaconsulta.get(0).getConsulta());
		List<Examenes> lista = new ArrayList<>();
		for (ConsultaExamen obj : listaconsulta) {
			Examenes examen = new Examenes(obj.getExamen(),obj.getInfoAdicional());
			lista.add(examen);
		}
		if(!bandera)
			consultas.getConsulta().setDetalleConsulta(null);
		consultas.setListaExamenes(lista);
		consultas.getConsulta().getMedico().setConsultas(null);
		consultas.getConsulta().setExamenes(null);
		return consultas;
	}

	@Override
	public Page<ConsultaExamen> listarPorIdCosnultaPaginado(Integer id, Integer page, Integer size) {
		return null; //repo.findByConsulta_Id(id, PageRequest.of(page, size));
	}

	@Override
	public ConsultaExamen traerPorId(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ConsultaExamen> traerPaginado(Integer pagina, Integer registros) {
		Page<ConsultaExamen> lista = repo.findAll(PageRequest.of(pagina, registros));	
		for (ConsultaExamen obj : lista) {
			obj.getConsulta().setMedico(null);
			obj.getConsulta().setExamenes(null);
		}
		return lista;
	}

	@Override
	public void recepcionDatos(ConsultaExamenDto datos) throws ModelNotFoundException {
		Consulta consulta = repoConsulta.findById(datos.getIdConsulta()).orElseThrow(()->new ModelNotFoundException("La consulta ingresada no existe en la base de datos"));
		Examen examen = repoExamen.findById(datos.getIdExamen()).orElseThrow(()->new ModelNotFoundException("El examen ingresado no existe"));
		ConsultaExamen conex = new ConsultaExamen();
		conex.setConsulta(consulta);
		conex.setExamen(examen);
		conex.setInfoAdicional(datos.getInfoAdicional());
		System.out.println("idConsulta : "+consulta.getId());
		System.out.println("idExamen : "+examen.getId());
		System.out.println("termine las consultas y se creo el consultaExamen");
		this.guardar(conex);
	}

	@Override
	public Page<VistaMedicos> traerVistaMedicos(Paginado page) {
		Page<VistaMedicos> lista = repoVista.consultarVista(PageRequest.of(page.getPagina(), page.getRegistros(),Sort.Direction.ASC,"id_consulta"));
		return lista;
	}

	/*
	@Override
	public void eliminarConsultaExamen(Integer idExamen, Integer idConsulta) {
		repo.eliminar(idExamen, idConsulta);
	}
*/
	@Override
	public void eliminarConsultaExamen(Integer idConsulta,Integer idExamen) throws ModelNotFoundException {
		System.out.println(repoConsulta.buscarPorId(idConsulta));
		System.out.println(repoExamen.traerExamenId(idExamen));
		if(repoConsulta.buscarPorId(idConsulta)<=0)
				throw new ModelNotFoundException("la consulta solicitada no tiene examenes asociados o directamente no existe en la base de datos");
		
		if(repoExamen.traerExamenId(idExamen)<=0)
				throw new ModelNotFoundException("el examen solicitado no tiene consultas asociadas o directamente no existe en la base de datos");
		repo.eliminar(idExamen, idConsulta);
	}

	@Override
	public void editarConsultaExamen(ConsultaExamenDto datos) throws ModelNotFoundException {
		if(repoConsulta.buscarPorId(datos.getIdConsulta())<=0)
				throw new ModelNotFoundException("la consulta solicitada no tiene examenes asociados o directamente no existe en la base de datos");
		
		if(repoExamen.traerExamenId(datos.getIdExamen())<=0)
				throw new ModelNotFoundException("el examen solicitado no tiene consultas asociadas o directamente no existe en la base de datos");
		repo.editar(datos.getInfoAdicional(), datos.getIdExamen(), datos.getIdConsulta());
	}
	
	
	
}
