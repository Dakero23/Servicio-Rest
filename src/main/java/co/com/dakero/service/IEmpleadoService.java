package co.com.dakero.service;

import java.util.List;

import co.com.dakero.entidad.EmpleadoDTO;
import co.com.dakero.entidad.EmpleadoEntity;

public interface IEmpleadoService extends ICRUD<EmpleadoEntity, Integer>{

	List<EmpleadoDTO> listarResumen();

}
