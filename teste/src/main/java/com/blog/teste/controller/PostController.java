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

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.teste.model.Comentario;
import com.blog.teste.model.Post;
import com.blog.teste.model.Usuario;
import com.blog.teste.payload.response.MessageResponse;
import com.blog.teste.service.ComentarioService;
import com.blog.teste.service.PostService;
import com.blog.teste.service.UsuarioService;

@RestController
@RequestMapping(value="/api")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ComentarioService comentarioService;
	
	@Autowired
	UsuarioService usuarioService;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagens";
	
	@GetMapping(value = "/posts")
	public Iterable<Post> getPosts() {
		return postService.findAll();
	}
	
	@GetMapping(value = "/posts/{id}")
	public Optional<Post> getPostsById(@PathVariable(value="id") String id) {
		return postService.findById(id);
	}
		
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(value = "/newpost")
	public Post savePost(@RequestBody Post post, MultipartFile imagem, HttpServletRequest request) throws IOException {
			
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
			post.setAutor(request.getRemoteUser());
			
		}
		
		post.setAutor(request.getRemoteUser());	
		post.setData(LocalDate.now());
		return postService.save(post);
	}
	
	@PutMapping(value="/post/{id}")
	public Post inserirComentarios(@PathVariable("id") String idPost, @RequestBody Comentario comentario, HttpServletRequest request) {
		
			Optional<Post> optionalPost = postService.findById(idPost);
			Post post = optionalPost.get();		
			
				List<Comentario> coment = new ArrayList<>();
				
				coment.add(comentario);
				
				post.setComentarios(coment);
				comentario.setAutor(request.getRemoteUser());
				comentario.setPost(idPost);
				
				comentarioService.save(comentario);
			
		return postService.save(post);
			
	}
	
	 @DeleteMapping("/post") 
	 public ResponseEntity<?> deletePost(@RequestBody Post post, HttpServletRequest request) { 
		 
	 List<Comentario> comentarios = new ArrayList<>();
	 
	 String remoteUser = request.getRemoteUser();
	 
	 Optional<Post> pId = postService.findById(post.getId());
	 Post p = pId.get();
	 
	 if(!remoteUser.equals(p.getAutor())) {
		 return ResponseEntity.badRequest()
					.body(new MessageResponse("Somente o autor tem autorização para exclusão"));
	 }else {
			 Optional<Post> optionalPost = postService.findById(post.getId());
			 Post postGet = optionalPost.get();	
			 
			 comentarios.addAll(postGet.getComentarios());
			 
			 comentarioService.deleteAll(comentarios);
			 
			 postService.delete(postGet, comentarios, uploadDirectory);
			
			 return ResponseEntity.ok(new MessageResponse("Deletado com sucesso")); 
	 	}
	 }
	 
	
	@DeleteMapping("/comentario")
	public ResponseEntity<?> deleteComentario(@RequestBody Comentario comentario, HttpServletRequest request) {
		
		 comentario = comentarioService.findById(comentario.getId());
		 String remoteUser = request.getRemoteUser();
		 
		 if(!remoteUser.equals(comentario.getAutor())) {
			 return ResponseEntity.badRequest()
						.body(new MessageResponse("Somente o autor tem autorização para exclusão"));
		 }else {	
			comentarioService.delete(comentario);
			return ResponseEntity.ok(new MessageResponse("Deletado com sucesso"));
		 }
	}

	
	@PutMapping("/post")
	public Post updatePost(@RequestBody Post post, String id) {	
		return postService.save(post);
	}
}
