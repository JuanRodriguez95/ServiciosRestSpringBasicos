package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.unicundi.proyectoSpringPrueba.entity.Profesor;

@Repository
public interface IProfesorRepo extends JpaRepository<Profesor, Integer> {
    
    @Query(value = "SELECT * FROM profesor WHERE cedula = ?1",nativeQuery = true)
    Profesor buscarPorcedulaSQL(String cedula);
   
    Profesor findBycedula(String cedula);

    @Query(value = "SELECT p FROM profesor p WHERE p.cedula = :cedula")
    Profesor buscarPorcedulaJPQL(@Param("cedula") String cedula);

    @Query( value = "SELECT COUNT(id) FROM profesor WHERE id =?1",nativeQuery = true)
    Integer buscarPorId (Integer id);
    
    

    
}
