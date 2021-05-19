package com.blog.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.teste.model.Comentario;
import com.blog.teste.model.Post;
import com.blog.teste.repository.PostRepository;

@Service
public class PostService{
	
	@Autowired
	private PostRepository repository;
	
	public Optional<Post> findById(String id){
		return repository.findById(id);
	}

	public Iterable<Post> findAll() {
		return repository.findAll();
	}

	public void delete(Post post, List<Comentario> comentarios, String imageName) {
		repository.deleteById(post.getId());
	}

	public Post save(Post post) {
		return repository.save(post);
	}

	public Post save(Optional<Post> optionalPost) {
		return repository.save(optionalPost);
		
	}

}
