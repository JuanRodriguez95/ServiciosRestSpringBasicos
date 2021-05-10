package co.edu.unicundi.proyectoSpringPrueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicundi.proyectoSpringPrueba.entity.Medico;
import co.edu.unicundi.proyectoSpringPrueba.exception.EmptyListException;
import co.edu.unicundi.proyectoSpringPrueba.exception.OrderNotFoundException;

@Repository
public interface IMedicoRepo extends JpaRepository<Medico,Integer> {
    

    @Query(value = "SELECT COUNT(id) FROM medico WHERE id =?1",nativeQuery = true)
    public Long contarRegistros(@Param("id")Integer id);

    @Query(value = "SELECT * FROM medico WHERE id =?1",nativeQuery = true)
    public Medico traerMedico(@Param("id")Integer id);

    @Query(value = "SELECT COUNT(correo) FROM medico WHERE correo =?1",nativeQuery = true)
    public Long contarCorreo(@Param("correo")String correo);

    Page<Medico> findByNombreIgnoreCaseOrderByDireccion_BarrioAsc(@Param("nombre") String nombre,Pageable page);

    Page<Medico> findByApellidoIgnoreCaseOrderByDireccion_BarrioDesc(@Param("apellido")String apellido,Pageable page);

    Medico findByCorreoIgnoreCase(@Param("correo")String correo);

    Page<Medico> findByDireccion_DetalleIgnoreCaseOrderByDireccion_CiudadAsc(@Param("detalle")String detalle,Pageable page);

    Page<Medico> findByDireccion_CiudadIgnoreCaseOrderByNombreAsc(@Param("ciudad") String ciudad,Pageable page);

    Page<Medico> findByDireccion_PaisIgnoreCaseOrderByApellidoDesc(@Param("pais")String pais,Pageable page);

    Page<Medico> findByDireccion_BarrioIgnoreCase(@Param("barrio")String barrio,Pageable page);



}
