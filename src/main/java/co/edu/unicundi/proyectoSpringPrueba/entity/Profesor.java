package co.edu.unicundi.proyectoSpringPrueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;




@Entity(name = "profesor")

//@NamedQuery(name = "Profesor.buscarPorcedulaJPQL",query = "SELECT p FROM profesor p WHERE p.cedula = :cedula")

@Table(name = "profesor")
public class Profesor {
    @ApiModelProperty(position = 0, notes = "valor autoincremental que actua como llave primaria en la base de datos")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(position = 1,notes=" Nombre del profesor")
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "Nombre debe tener entre 3 y 15 caracteres")
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @ApiModelProperty(position = 2,notes="Apellido del profesor")
	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "Apellido debe tener entre 3 y 15 caracteres")
    @Column(name = "apellido",nullable = false)
    private String apellido;

    @ApiModelProperty(position = 3,notes="El correo es erroneo")
    @Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$",message = "el correo no tiene el formato correcto ( ejemplo@gmail.com")
    //               ^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$
    @Column(name = "correo")
    private String correo;

    @ApiModelProperty(position = 4,notes="Numero de identificacion del profesor, este numero es unico para cada registro")
	@NotNull(message = "La edad es obligatoria")
	@Min(value = 18,message = "la edad debe ser mayor a 18 años")
    @Max(value = 80,message = "la edad debe ser menor a 80 años")
    @Column(name = "edad",nullable = false)
    private Integer edad;
 
    @ApiModelProperty(position = 5,notes="Numero de identificacion del profesor, este numero es unico para cada registro")
	@NotNull(message = "la Cedula es obligatoria")
    @Pattern(regexp="^[0-9]{8,18}$",message = "la cedula no cumple con el formato establecido, las cedulas actuales superan los 6 digitos (ejemplo= 1234567")
    @Column(name = "cedula",nullable = false,unique = true)
    private String cedula;


    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }
    public String getCorreo() {
        return correo;
    }
    public Integer getEdad() {
        return edad;
    }
    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
