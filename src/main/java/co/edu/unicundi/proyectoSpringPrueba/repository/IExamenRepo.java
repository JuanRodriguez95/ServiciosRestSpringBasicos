package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Examen;

@Repository
public interface IExamenRepo extends JpaRepository<Examen, Integer> {
    
    
    @Query(value = "SELECT COUNT(id) FROM examen WHERE id = ?1",nativeQuery = true)
    Integer traerExamenId(@Param("id")Integer id);

    
    
}
