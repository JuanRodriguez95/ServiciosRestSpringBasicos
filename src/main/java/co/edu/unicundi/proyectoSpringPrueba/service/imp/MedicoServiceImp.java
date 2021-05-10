package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.dto.Paginado;
import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.ConsultaExamen;
import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IMedicoRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IMedicoService;

@Service
public class MedicoServiceImp implements IMedicoService {

    @Autowired
    private IMedicoRepo repo;

    @Autowired
    private ConsultaServiceImp conService;

    @Override
    public Medico traerPorId(Integer id) throws ModelNotFoundException {
        Medico medico = repo.findById(id).orElseThrow(()->new ModelNotFoundException("el medico con el id solicitado no se encuentra registrado"));
        return medico;
    }

    @Override
    public Page<Medico> traerPaginado(Integer pagina, Integer registros) {
        return repo.findAll(PageRequest.of(pagina, registros));
    }

    @Override
    public void guardar(Medico medico) throws ConflictException {
        System.out.println(repo.contarCorreo(medico.getCorreo()));
        if(repo.contarCorreo(medico.getCorreo())>0)
            throw new ConflictException("el correo ingresado ya se encuentra registrado");
        medico.getDireccion().setMedico(medico);
        repo.save(medico);
    }

    @Override
    public void editar(Medico medico) throws ModelNotFoundException {
        Medico medicoaux = traerPorId(medico.getId());
        medicoaux.setNombre(medico.getNombre());
        medicoaux.setApellido(medico.getApellido());
        medicoaux.setCorreo(medico.getCorreo());
        medicoaux.getDireccion().setDetalle(medico.getDireccion().getDetalle());
        medicoaux.getDireccion().setBarrio(medico.getDireccion().getBarrio());
        medicoaux.getDireccion().setCiudad(medico.getDireccion().getCiudad());
        medicoaux.getDireccion().setPais(medico.getDireccion().getPais());
        repo.save(medicoaux);
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Long cantidad = repo.contarRegistros(id);
        if (cantidad <= 0) {
            throw new ModelNotFoundException("El registro no existe");
        }
        repo.deleteById(id);
    }

    @Override
    public Medico traerMedicoConsultas(Integer idMedico, Boolean consultas) throws ModelNotFoundException {
        pruebaRepo();
        Medico medico = repo.findById(idMedico)
                .orElseThrow(() -> new ModelNotFoundException("El Registro solicitado no se encuentra resgistrado"));
        if (!consultas) {
            medico.setConsultas(null);
        } else {
            for (Consulta obj : medico.getConsultas()) {
                obj.setMedico(null);
                 for (ConsultaExamen conEx : obj.getExamenes()) {
                     conEx.setConsulta(null);
                 }
            }
        }
        return medico;
    }

    public void pruebaRepo() {
/*
        System.out.println("findByNombreOrApellido : " + repo.findByNombreOrApellidoIgnoreCase("Roberto", "noooo").toString());
        System.out.println("findByCorreo :" + repo.findByCorreoIgnoreCase("roberto@gmail.com").toString());
        System.out.println("findByDireccion_Detalle : " + repo.findByDireccion_DetalleIgnoreCase("Frente a la alcaldia").toString());
        System.out.println("findByDireccion_Ciudad : " + repo.findByDireccion_CiudadIgnoreCase("El Rosal").toString());
        System.out.println("findByDireccion_Pais : " + repo.findByDireccion_PaisIgnoreCase("colombia").toString());
        System.out.println("findByDireccion_Barrio :" + repo.findByDireccion_BarrioIgnoreCase("LLeras").toString());
*/
    }

    @Override
    public Page<Medico> traerMedicosPaginadosOrdenados(Paginado datos) throws EmptyListException,
            OrderNotFoundException {
        Page<Medico> listaMedico = null;
		if(validar(datos.getAtributo())){
			if(datos.getOrden().equals("ASD")){
				listaMedico=this.repo.findAll(PageRequest.of(datos.getPagina(),datos.getRegistros(),Sort.Direction.ASC,datos.getAtributo()));
			}else{
				if(datos.getOrden().equals("DSD")){
					listaMedico=this.repo.findAll(PageRequest.of(datos.getPagina(),datos.getRegistros(),Sort.Direction.DESC,datos.getAtributo()));
				}else{
					throw new OrderNotFoundException("el tipo de ordenamiento  ("+ datos.getOrden() +")  es erroneo - ejemplo: (ASD = ASCENDENTE) (DSD = DESCENDENTE) ");
				}
			}
		}else{
			throw new OrderNotFoundException("El atributo  ("+datos.getAtributo() +")  por el que desea ordenar los registros no existe");
		}		
		if(listaMedico.getContent().isEmpty())
			throw new EmptyListException("No hay registros para mostrar en esta pagina");
		for (Medico medico : listaMedico) {
			medico.setConsultas(null);
		}
		return listaMedico;
    }


    public boolean validar(String atributo){
        if((atributo.equals("id"))||(atributo.equals("nombre"))||(atributo.equals("apellido"))||(atributo.equals("correo"))||(atributo.equals("direccion.barrio"))||(atributo.equals("direccion.ciudad"))||(atributo.equals("direccion.pais"))){
            return true;
        }
        return false;
    }

    @Override
    public Page<Medico> traerMedicoPorNombre(String nombre,Paginado datos)throws ModelNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = repo.findByNombreIgnoreCaseOrderByDireccion_BarrioAsc(nombre,PageRequest.of(datos.getPagina(), datos.getRegistros()));
        if(listaMedicos.getContent().isEmpty())
        throw new EmptyListException("No hay registros para mostrar en esta pagina");
            for (Medico medico : listaMedicos) {
                medico.setConsultas(null);
            }  
    return listaMedicos;
    }

    @Override
    public Page<Medico> traerMedicoPorApellido(String apellido,Paginado datos) throws ModelNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = repo.findByApellidoIgnoreCaseOrderByDireccion_BarrioDesc(apellido, PageRequest.of(datos.getPagina(), datos.getRegistros()));
        if(listaMedicos.getContent().isEmpty())
			 throw new EmptyListException("No hay registros para mostrar en esta pagina");
        for (Medico medico : listaMedicos) {
                medico.setConsultas(null);
        }  
        return listaMedicos;
    }

    @Override
    public Medico traerMedicoPorCorreo(String correo)throws ModelNotFoundException {
        Medico medico = repo.findByCorreoIgnoreCase(correo);
        if(medico==null)
            throw new ModelNotFoundException("No hay doctores registrados con el nombre: "+ correo +" solicitado");
        medico.setConsultas(null);
        return medico;
    }

    @Override
    public Page<Medico> traerMedicoPorDetalleDireccion(String detalle,Paginado datos)throws ModelNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = repo.findByDireccion_DetalleIgnoreCaseOrderByDireccion_CiudadAsc(detalle,PageRequest.of(datos.getPagina(), datos.getRegistros()));
        if(listaMedicos.getContent().isEmpty())
			 throw new EmptyListException("No hay registros para mostrar en esta pagina");
        for (Medico medico : listaMedicos) {
                medico.setConsultas(null);
        }  
        return listaMedicos;
    }

    @Override
    public Page<Medico> traerMedicoPorPais(String pais,Paginado page) throws OrderNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = repo.findByDireccion_PaisIgnoreCaseOrderByApellidoDesc(pais, PageRequest.of(page.getPagina(), page.getRegistros()));   
        if(listaMedicos.getContent().isEmpty())
			 throw new EmptyListException("No hay registros para mostrar en esta pagina");
        for (Medico medico : listaMedicos) {
                medico.setConsultas(null);
        }  
        return listaMedicos;
    }

    @Override
    public Page<Medico> traerMedicoPorCiudad(String ciudad,Paginado page) throws OrderNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = repo.findByDireccion_CiudadIgnoreCaseOrderByNombreAsc(ciudad, PageRequest.of(page.getPagina(), page.getRegistros()));        
        for (Medico medico : listaMedicos) {
            medico.setConsultas(null);
        } 
        if(listaMedicos.getContent().isEmpty())
            throw new EmptyListException("No hay registros para mostrar en esta pagina");
        
        return listaMedicos;
    }

    @Override
    public Page<Medico> traerMedicoPorBarrio(String barrio,Paginado page) throws OrderNotFoundException,
            EmptyListException {
        Page<Medico> listaMedicos = null;
        if(page.getOrden().equals("ASD")){
            listaMedicos = repo.findByDireccion_BarrioIgnoreCase(barrio, PageRequest.of(page.getPagina(), page.getRegistros(),Sort.Direction.ASC,"nombre"));           
        }else{ 
            if(page.getOrden().equals("DSD")){
                listaMedicos = repo.findByDireccion_BarrioIgnoreCase(barrio, PageRequest.of(page.getPagina(), page.getRegistros(),Sort.Direction.DESC,"nombre"));                   
            }else{
                throw new OrderNotFoundException("el tipo de ordenamiento  ("+ page.getOrden() +")  es erroneo - ejemplo: (ASD = ASCENDENTE) (DSD = DESCENDENTE) ");
            }
        }
        if(listaMedicos.getContent().isEmpty())
            throw new EmptyListException("No hay registros para mostrar en esta pagina");
        for (Medico medico : listaMedicos) {
            medico.setConsultas(null);
        } 
        return listaMedicos;
    }
    
    

    
}
