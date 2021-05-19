package com.blog.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.teste.model.Post;
import com.blog.teste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Usuario findByUsuario(String usuario);
	
	Boolean existsByUsuario(String usuario);

	void deleteById(String id);

	Usuario save(Optional<Usuario> optionalUsuario);
}
