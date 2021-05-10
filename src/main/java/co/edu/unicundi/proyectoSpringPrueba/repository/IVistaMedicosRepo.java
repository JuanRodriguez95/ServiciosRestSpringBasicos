package co.edu.unicundi.proyectoSpringPrueba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.view.VistaMedicos;



@Repository
public interface IVistaMedicosRepo extends JpaRepository<VistaMedicos, Integer>{

	@Query(value = "SELECT * FROM vista_medicos",nativeQuery = true)
	Page<VistaMedicos> consultarVista(Pageable page);
	
}
