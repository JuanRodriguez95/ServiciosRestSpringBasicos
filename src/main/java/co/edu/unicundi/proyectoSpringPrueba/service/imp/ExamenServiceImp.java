package co.edu.unicundi.proyectoSpringPrueba.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;
import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.repository.IExamenRepo;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.IExamenService;

@Service
public class ExamenServiceImp implements IExamenService {

    @Autowired
    private IExamenRepo repo;
    
    @Override
    public Examen traerPorId(Integer id) throws ModelNotFoundException {
        return repo.findById(id).orElseThrow(()-> new ModelNotFoundException("El examen con el id especificado no existe"));
    }

    @Override
    public Page<Examen> traerPaginado(Integer pagina, Integer registros) {
        return repo.findAll(PageRequest.of(pagina, registros));
    }

    @Override
    public void guardar(Examen examen) throws ConflictException, ModelNotFoundException {
        repo.save(examen);
    }

    @Override
    public void editar(Examen examen) throws ModelNotFoundException {
        Examen examenAux = traerPorId(examen.getId());
        examenAux.setDescripcion(examen.getDescripcion());
        examenAux.setNombre(examen.getNombre());
        examenAux.setPreparacion(examen.getPreparacion());
    }

    @Override
    public void eliminar(Integer id) throws ModelNotFoundException {
        Integer examenes = repo.traerExamenId(id);
        if(examenes<=0){
            throw new ModelNotFoundException("el examen con el id ingresado no existe");
        }
        repo.deleteById(id);
    }



}
