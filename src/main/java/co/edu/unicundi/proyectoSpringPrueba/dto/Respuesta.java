package co.edu.unicundi.proyectoSpringPrueba.dto;

import org.springframework.data.domain.Page;

import co.edu.unicundi.proyectoSpringPrueba.exception.ConflictException;
import co.edu.unicundi.proyectoSpringPrueba.exception.ModelNotFoundException;
import co.edu.unicundi.proyectoSpringPrueba.service.Interface.ICrud;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class Respuesta implements ICrud<Respuesta, Integer> {

	@ApiModelProperty(position = 1, notes = "Mensaje de respuesta")
	@ApiParam(name = "Mensaje de respuesta")
	private String respuesta;

	@ApiModelProperty(position = 2, notes = "atributo de tipo Object que almacena el resgistro consultado")
	@ApiParam(name = "atributo de tipo Object que almacena el resgistro consultado")
	private Object registro;

	@ApiModelProperty(position = 0, notes = "Codigo de respuesta arrojado por el servicio")
	@ApiParam(name = "Codigo de respuesta arrojado por el servicio")
	private int codigo;

	public Respuesta(String respuesta, Object registro, int codigo) {
		super();
		this.respuesta = respuesta;
		this.registro = registro;
		this.codigo = codigo;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Object getRegistro() {
		return registro;
	}

	public void setRegistro(Object registro) {
		this.registro = registro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public Respuesta traerPorId(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Respuesta> traerPaginado(Integer pagina, Integer registros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Respuesta entity) throws ConflictException, ModelNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(Respuesta entity) throws ModelNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub

	}
}
