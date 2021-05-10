package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IConsultaService;

@Service
public class ConsultaServiceImp implements IConsultaService{

	@Autowired
	private IConsultaRepo repo;

	@Autowired
	private IExamenRepo repoExamen;

	@Autowired 
	private MedicoServiceImp medicoService;

	@Autowired
	private ExamenServiceImp examenService;
	



	/*
	@Override
	public Page<Consulta> retornar(Paginado datos) throws EmptyListException,
			OrderNotFoundException {		
		Page<Consulta> listaCon = null;
		if(validar(datos.getOrden())){
			if(datos.getOrden().equals("ASD")){
				listaCon=this.repo.findAll(PageRequest.of(datos.getPagina(), datos.getRegistros(),Sort.Direction.ASC,datos.getAtributo()));
			}else{
				if(datos.getOrden().equals("DSD")){
					listaCon=this.repo.findAll(PageRequest.of(datos.getPagina(), datos.getRegistros(),Sort.Direction.DESC,datos.getAtributo()));
				}else{
					throw new OrderNotFoundException("el tipo de ordenamiento "+ datos.getOrden() +" es erroneo - ejemplo: (ASD = ASCENDENTE) (DSD = DESCENDENTE) ");
				}
			}
		}else{
			throw new OrderNotFoundException("El atributo "+datos.getAtributo()+" por el que desea ordenar los registros no existe");
		}		
		if(listaCon.getContent().isEmpty())
			throw new ModelNotFoundException("No hay registros para mostrar en esta pagina");
		for (Consulta consulta : listaCon) {
			consulta.setDetalleConsulta(null);
		}
		return listaCon;
	}
*/


	@Override
	public Consulta traerPorId(Integer id) throws ModelNotFoundException {
		Consulta consulta = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("la consulta con el id ingresado no se encuentra registrada en la base de datos"));
				consulta.getMedico().setConsultas(null);
				consulta.getMedico().setDireccion(null);
				for (ConsultaExamen obj : consulta.getExamenes()) {
					obj.setConsulta(null);
					obj.getExamen().setExamen(null);
				}
		return consulta;
	}

	@Override
	public Page<Consulta> traerPaginado(Integer pagina, Integer registros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Consulta consulta) {

	}

	@Override
	public void editar(Consulta consulta) throws ModelNotFoundException {
		Consulta con  = repo.findById(consulta.getId()).orElseThrow(
			() -> new ModelNotFoundException("la consulta con el id ingresado no se encuentra registrada en la base de datos"));
		con.setFecha(consulta.getFecha());
		repo.save(con);
	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		if((repo.buscarPorId(id)<=0))
			throw new ModelNotFoundException("No se encuentran registros con el id solicitado");
		repo.deleteById(id);
	}

	@Override
	public void guardarRelacion(Consulta consulta, Integer id) throws ModelNotFoundException {
		consulta.setMedico(medicoService.traerPorId(id));
		Integer secuencia = repo.traerSecuencia();
		System.out.println(secuencia);
		if(consulta.getDetalleConsulta() != null) {
			consulta.getDetalleConsulta().forEach(det -> {
				det.setConsulta(consulta);
			});
		}
		consulta.setId(secuencia);
		for (ConsultaExamen obj : consulta.getExamenes()) {
			obj.setConsulta(consulta);
			if(repoExamen.traerExamenId(obj.getExamen().getId())<=0)
				throw new ModelNotFoundException("el examen ingresado con id: "+ obj.getExamen().getId() +" no existe en la base de datos");
		}
		repo.save(consulta);
	}
	
}

