package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;

@Repository
public interface IDetalleConsultaRepo extends JpaRepository<DetalleConsulta, Integer>{
    

    @Query(value = "SELECT * FROM detalle_consulta WHERE id_consulta = ?1",nativeQuery = true)
    public Page<DetalleConsulta> traerDetallesConsulta(@Param("Id") Integer idConsulta,Pageable page);


    @Query( value = "SELECT COUNT(id) FROM detalle_consulta WHERE id =?1",nativeQuery = true)
    Integer buscarPorId (Integer id);
    
    //DetalleConsulta findFirstByconsulta_nombreDoctor(String nombreDoctor);

    Page<DetalleConsulta> findAllByconsulta_id(int id,Pageable paginado); 

}
