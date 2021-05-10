package co.edu.unicundi.proyectoSpringPrueba.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Paginado {
    
    @NotNull(message = "La pagina es obligatoria")
    @Min(value = 0,message = "La pagina no puede ser negaiva")
    private Integer pagina;

    @NotNull(message = "la cantidad de registros es obligatoria")
    @Min(value = 1,message = "Es obligatorio traer al menos un registro")
    private Integer registros;

    private String atributo;

    private String orden;

    

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    
}
