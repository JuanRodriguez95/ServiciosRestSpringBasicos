package co.edu.unicundi.proyectoSpringPrueba.service.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICrud2<T, V> extends JpaRepository<T, V> {
    
}
