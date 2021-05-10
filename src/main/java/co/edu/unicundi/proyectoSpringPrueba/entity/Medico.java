package co.edu.unicundi.proyectoSpringPrueba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medico")
public class Medico {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",nullable = false)
    @NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "Nombre debe tener entre 3 y 15 caracteres")
    private String nombre;

    @Column(name = "apellido",nullable = false)
    @NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    private String apellido;

    @Column(name = "correo",nullable = false)
    @Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$",message = "el correo no tiene el formato correcto ( ejemplo@gmail.com")
    private String correo;

    @OneToMany(mappedBy = "medico", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    @NotNull(message = "direccion obligatoria")
    @OneToOne(mappedBy = "medico",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Direccion direccion;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    //@JsonIgnore
    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return "Medico [apellido=" + apellido + ", consultas=" + consultas + ", correo=" + correo + ", direccion="
                + direccion + ", id=" + id + ", nombre=" + nombre + "]";
    }

    
    
}
