package co.com.dakero.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.dakero.entidad.EmpleadoDTO;

//import com.google.gson.Gson;

import co.com.dakero.entidad.EmpleadoEntity;
import co.com.dakero.exception.ModeloNotFoundException;
import co.com.dakero.service.IEmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api/empleado")
@Tag(name = "Dakero", description = "API consulta información empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService service;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "Consultar todos los Empleado", description = "Consulta información de todos los empleados calculando el tiempo vigente del contrato y la edad actual", tags = {
			"Consulta Empleado" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "429", description = "TOO_MANY_REQUESTS"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping
	public ResponseEntity<List<EmpleadoDTO>> listar() throws Exception {
		List<EmpleadoDTO> consulta = new ArrayList<>();
		consulta = service.listarResumen();
		return new ResponseEntity<List<EmpleadoDTO>>(consulta, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "Consulta información del empleado", description = "Consulta información del empleado mediante la llave primaria calculando el tiempo vigente del contrato y la edad actual", tags = {
			"Consulta Empleado Identificador" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "429", description = "TOO_MANY_REQUESTS"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		EmpleadoEntity empEntity = service.listarPorId(id);
		if (empEntity == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		EmpleadoDTO obj = new EmpleadoDTO(empEntity);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/**
	 * 
	 * @param p
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody EmpleadoEntity p) {
		// Creación del objeto
		System.out.println(p);
		EmpleadoEntity obj;
		try {
			obj = service.registrar(p);

			// contrucció ncon información de la persona creada
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(obj.getIdEmpleado()).toUri();

//		return new ResponseEntity<>(location, HttpStatus.OK);
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Error causado"+e, HttpStatus.OK);
		}

	}
}
