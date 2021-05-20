package com.blog.teste.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.teste.model.Foto;
import com.blog.teste.repository.FotoRepository;

@Service
public class FotoService{
	
	@Autowired
	private FotoRepository repository;
	
	public Foto findById(String id){
		return repository.findById(id);
	}

	public Iterable<Foto> findAll() {
		return repository.findAll();
	}

	public void delete(Foto foto) {
		repository.delete(foto);
	}

	public Foto save(Foto foto) {
		return repository.save(foto);
	}

	public void deleteAll(List<Foto> fotos) {
		
	}

}
