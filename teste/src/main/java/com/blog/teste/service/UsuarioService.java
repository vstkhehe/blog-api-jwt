package com.blog.teste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.teste.model.Usuario;
import com.blog.teste.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> findById(String id){
		return repository.findById(id);
	}

	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}

	public void delete(Usuario usuario) {
		repository.deleteById(usuario.getId());
	}

	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario save(Optional<Usuario> optionalUsuario) {
		return repository.save(optionalUsuario);
		
	}
	
	public Boolean existsByUsuario(String usuario) {
		return repository.existsByUsuario(usuario);
	}

}
