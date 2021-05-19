package com.blog.teste.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.teste.model.Comentario;
import com.blog.teste.model.Post;
import com.blog.teste.service.ComentarioService;
import com.blog.teste.service.PostService;

@RestController
@RequestMapping(value="/api")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ComentarioService comentarioService;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagens";
	
	@GetMapping(value = "/posts")
	public Iterable<Post> getPosts() {
		return postService.findAll();
	}
	
	@GetMapping(value = "/posts/{id}")
	public Optional<Post> getPostsById(@PathVariable(value="id") String id) {
		return postService.findById(id);
	}
		
	
	@PostMapping(value = "/newpost")
	public Post savePost(@RequestBody Post post, MultipartFile imagem) throws IOException {
			
		if(imagem != null) {
		
			String imagemNome = imagem.getOriginalFilename();
			String imagePath = Paths.get(uploadDirectory, imagemNome).toString();
			String imagemTipo = imagem.getContentType();
			long size = imagem.getSize();
			String imagemTamanho = String.valueOf(size);
			
			//Salvar a imagem
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imagePath)));
				stream.write(imagem.getBytes());
				stream.close();
		
			post.setImagemNome(imagemNome);
			post.setImagemTipo(imagemTipo);
			post.setImagemTamanho(imagemTamanho);
			
		}
			
		post.setData(LocalDate.now());
		return postService.save(post);
	}
	
	@PutMapping(value="/post/{id}")
	public Post inserirComentarios(@PathVariable("id") String idPost, @RequestBody Comentario comentario) {
		
			Optional<Post> optionalPost = postService.findById(idPost);
			Post post = optionalPost.get();	
			
			//comentario.setPost(id);
				
			/*
			 * comentario.stream() .forEach(coment -> coment.setPost(post.getId()));
			 */		
			
				List<Comentario> coment = new ArrayList<>();
				
				coment.add(comentario);
				
				post.setComentarios(coment);
				comentario.setPost(idPost);
				
				comentarioService.save(comentario);
			
		return postService.save(post);
			
	}
	
	
	 @DeleteMapping("/post") public void deletePost(@RequestBody Post post) { 
		 
	 List<Comentario> comentarios = new ArrayList<>();
	 
	 Optional<Post> optionalPost = postService.findById(post.getId());
		Post postGet = optionalPost.get();	
	 
	 comentarios.addAll(postGet.getComentarios());
	 
	 comentarioService.deleteAll(comentarios);
	 
	 postService.delete(postGet, comentarios, uploadDirectory); 
	 
	 }
	 
	
	@DeleteMapping("/deletarComentario/")
	public void deleteComentario(@RequestBody Comentario comentario) {
		comentario = comentarioService.findById(comentario.getId());
			comentarioService.delete(comentario);
	}

	
	@PutMapping("/post")
	public Post updatePost(@RequestBody Post post, String id) {	
		return postService.save(post);
	}
}
