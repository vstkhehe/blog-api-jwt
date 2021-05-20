package com.blog.teste.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.teste.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {

	Foto findById(String id);

	Foto save(List<Foto> foto);

	void delete(Foto foto);
}
