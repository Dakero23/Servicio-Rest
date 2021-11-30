package co.com.dakero.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.dakero.entidad.EmpleadoDTO;
import co.com.dakero.entidad.EmpleadoEntity;
import co.com.dakero.repo.IEmpleadoRepo;
import co.com.dakero.repo.IGenericRepo;
import co.com.dakero.service.IEmpleadoService;

@Service
public class EmpleadoServiceImpl extends CRUDImpl<EmpleadoEntity, Integer> implements IEmpleadoService{

	@Autowired
	private IEmpleadoRepo repo;

	@Override
	protected IGenericRepo<EmpleadoEntity, Integer> getRepo() {
		return repo;
	}

	@Transactional
	@Override
	public List<EmpleadoDTO> listarResumen() {

		
		List<EmpleadoDTO> consultas = new ArrayList<>();
		repo.findAll().forEach(x -> {
			EmpleadoDTO cr = new EmpleadoDTO(x);
			consultas.add(cr);
		});
		
		return consultas;
	}

}
