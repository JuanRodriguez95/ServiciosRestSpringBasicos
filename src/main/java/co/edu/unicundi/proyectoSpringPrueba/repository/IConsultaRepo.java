package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Consulta;
import co.edu.unicundi.proyectoSpringPrueba.entity.DetalleConsulta;

@Repository
public interface IConsultaRepo extends  JpaRepository<Consulta, Integer>{

    //@Query(value = )
	//List<Consulta> findAllPaginado(Consulta consulta, Pageable pagina);
	
    @Query( value = "SELECT COUNT(id) FROM consulta WHERE id =?1",nativeQuery = true)
    Integer buscarPorId (Integer id);

    //Consulta findBydetalleConsulta_consulta_id(int idMedico);

    //Consulta findBynombreDoctorAndId(String nombre,int id);

    @Query(value = "select nextval('consulta_id_seq')",nativeQuery = true)
    Integer traerSecuencia();

}