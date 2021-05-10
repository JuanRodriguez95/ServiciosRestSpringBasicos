package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;

public interface ICrud <T,V>{
    
    public T traerPorId(V id)throws ModelNotFoundException;

    public Page<T> traerPaginado(Integer pagina , Integer registros );

    public void guardar(T entity)throws ConflictException, ModelNotFoundException;
 
    public void editar(T entity)throws ModelNotFoundException;

    public void eliminar(V id)throws ModelNotFoundException;

}
