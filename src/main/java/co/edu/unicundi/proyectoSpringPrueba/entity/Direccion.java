package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direccion")
public class Direccion {
    

    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "detalle es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    @Column(name = "detalle",nullable = false)
    private String detalle;

    @NotNull(message = "barrio es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    @Column(name = "barrio",nullable = false)
    private String barrio;

    @NotNull(message = "ciudad es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    @Column(name = "ciudad",nullable = false)
    private String ciudad;

    @NotNull(message = "pais es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    @Column(name = "pais",nullable = false)
    private String pais;

    @OneToOne
    @MapsId
    private Medico medico;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @JsonIgnore
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    

}
