package com.blog.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.teste.model.Album;
import com.blog.teste.model.Post;

public interface AlbumRepository extends JpaRepository<Album, String> {

	Optional<Album> findById(String id);

	void deleteById(String id);

	Album save(Optional<Album> optionalPost);
}
