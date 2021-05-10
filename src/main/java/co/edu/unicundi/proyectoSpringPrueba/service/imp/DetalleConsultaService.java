package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.repository.IDetalleConsultaRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IDetalleConsultaService;

@Service
public class DetalleConsultaService implements IDetalleConsultaService {

    @Autowired
	private IDetalleConsultaRepo repo;

    @Autowired
    private ConsultaServiceImp repoCon;

 
    @Override
    public DetalleConsulta traerPorId(Integer id) throws ModelNotFoundException {
        DetalleConsulta detalle = repo.findById(id).orElseThrow(()-> new ModelNotFoundException("el registro con el id solicitado no existe"));
		return detalle;
    }

    @Override
    public void guardarRelacion(DetalleConsulta detalle,Integer idConsulta) throws ModelNotFoundException {
        Consulta con = repoCon.traerPorId(idConsulta); 
        detalle.setConsulta(con);
        repo.save(detalle);
    }

    @Override
    public void editar(DetalleConsulta detal) throws ModelNotFoundException {
        DetalleConsulta detalle = this.traerPorId(detal.getId());
        detalle.setDiagnostico(detal.getDiagnostico());
        detalle.setTratamiento(detal.getTratamiento());
        repo.save(detalle);

    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {   
        if(repo.buscarPorId(id)<=0)
            throw new ModelNotFoundException("No se encuentra el registro con el id solicitado");    
        repo.deleteById(id);
    }

    @Override
    public Page<DetalleConsulta> traerPaginado(Integer pagina, Integer registros) {  
        //Page<DetalleConsulta> listaDetalle = repo.findAllByconsulta_id(id, paginado)    
        return null;
    }
    @Override
    public void guardar(DetalleConsulta detalledit)   {      
    }

}
