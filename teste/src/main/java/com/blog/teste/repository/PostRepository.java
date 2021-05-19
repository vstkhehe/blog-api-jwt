package com.blog.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.teste.model.Post;

public interface PostRepository extends JpaRepository<Post, String> {

	Optional<Post> findById(String id);

	void deleteById(String id);

	Post save(Optional<Post> optionalPost);
}
