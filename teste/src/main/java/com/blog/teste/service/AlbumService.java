package com.blog.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.teste.model.Foto;
import com.blog.teste.model.Album;
import com.blog.teste.repository.AlbumRepository;

@Service
public class AlbumService{
	
	@Autowired
	private AlbumRepository repository;
	
	public Optional<Album> findById(String id){
		return repository.findById(id);
	}

	public Iterable<Album> findAll() {
		return repository.findAll();
	}

	public void delete(Album post, List<Foto> comentarios, String imageName) {
		repository.deleteById(post.getId());
	}

	public Album save(Album post) {
		return repository.save(post);
	}

	public Album save(Optional<Album> optionalAlbum) {
		return repository.save(optionalAlbum);
		
	}

}
