package com.jamescurso.microservicios.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamescurso.microservicios.commons.alumnos.models.entity.Alumno;
import com.jamescurso.microservicios.commons.services.CommonServiceImpl;
import com.jamescurso.microservicios.usuarios.client.CursoFeignClient;
import com.jamescurso.microservicios.usuarios.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Autowired
	private CursoFeignClient clienteCurso;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		clienteCurso.eliminarCursoAlumnoPorId(id);
	}

	@Override
	@Transactional
	public void deletedById(Long id) {
		super.deletedById(id);
		this.eliminarCursoAlumnoPorId(id);
	}

}