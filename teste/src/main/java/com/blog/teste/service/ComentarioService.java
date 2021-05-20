package com.blog.teste.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.teste.model.Comentario;
import com.blog.teste.repository.ComentarioRepository;

@Service
public class ComentarioService{
	
	@Autowired
	private ComentarioRepository repository;
	
	public Comentario findById(String id){
		return repository.findById(id);
	}

	public Iterable<Comentario> findAll() {
		return repository.findAll();
	}

	public void delete(Comentario comentario) {
		repository.delete(comentario);
	}

	public Comentario save(Comentario comentario) {
		return repository.save(comentario);
	}

	public void deleteAll(List<Comentario> comentarios) {
		
	}

}
