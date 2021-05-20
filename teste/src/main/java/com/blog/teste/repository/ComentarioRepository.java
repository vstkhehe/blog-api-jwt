package com.blog.teste.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.teste.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	Comentario findById(String id);

	Comentario save(List<Comentario> comentario);

	void delete(Comentario comentario);
}
