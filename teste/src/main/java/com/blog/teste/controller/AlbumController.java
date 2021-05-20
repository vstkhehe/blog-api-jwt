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

import com.blog.teste.model.Album;
import com.blog.teste.model.Foto;
import com.blog.teste.payload.response.MessageResponse;
import com.blog.teste.service.AlbumService;
import com.blog.teste.service.FotoService;
import com.blog.teste.service.UsuarioService;

@RestController
@RequestMapping(value="/api")
public class AlbumController {

	@Autowired
	AlbumService albumService;
	
	@Autowired
	FotoService fotoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagens";
	
	@GetMapping(value = "/albums")
	public Iterable<Album> getAlbums() {
		return albumService.findAll();
	}
	
	@GetMapping(value = "/albums/{id}")
	public Optional<Album> getAlbumById(@PathVariable(value="id") String id) {
		return albumService.findById(id);
	}
		
	@PostMapping(value = "/newalbum")
	public Album savePost(@RequestBody Album album, HttpServletRequest request) throws IOException {
		
		album.setAutor(request.getRemoteUser());
		return albumService.save(album);
	}
	
	@PutMapping(value="/album/{id}")
	public ResponseEntity<MessageResponse> inserirFotos(@PathVariable("id") String id, @RequestBody Foto foto, Album album, 
														MultipartFile imagem, HttpServletRequest request) throws IOException {
		
		String remoteUser = request.getRemoteUser();
		
		Optional<Album> optionalAlbum = albumService.findById(id);
		
		album = optionalAlbum.get();
		 
		 if(!remoteUser.equals(album.getAutor())) {
			 return ResponseEntity.badRequest()
						.body(new MessageResponse("Somente o autor tem autorização para incluir"));
		 }else {			
			
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
				
					foto.setImagemNome(imagemNome);
					foto.setImagemTipo(imagemTipo);
					foto.setImagemTamanho(imagemTamanho);
					
				}

			 
			 
				List<Foto> f = new ArrayList<>();
				
				f.add(foto);
				
				album.setFotos(f);
				foto.setAlbum(id);
				foto.setData(LocalDate.now());
				
				albumService.save(album);
				fotoService.save(foto);
			
				return ResponseEntity.ok(new MessageResponse("Foto inserida"));
		 }
	}
	
	 @DeleteMapping("/album") 
	 public ResponseEntity<?> deleteAlbum(@RequestBody Album album, HttpServletRequest request) { 
		 
	 List<Foto> fotos = new ArrayList<>();
	 
	 String remoteUser = request.getRemoteUser();
	 
	 Optional<Album> pId = albumService.findById(album.getId());
	 Album p = pId.get();
	 
	 if(!remoteUser.equals(p.getAutor())) {
		 return ResponseEntity.badRequest()
					.body(new MessageResponse("Somente o autor tem autorização para exclusão"));
	 }else {
			 Optional<Album> optionalAlbum = albumService.findById(album.getId());
			 Album albumGet = optionalAlbum.get();	
			 
			 fotos.addAll(albumGet.getFotos());
			 
			 fotoService.deleteAll(fotos);
			 
			 albumService.delete(albumGet, fotos, uploadDirectory);
			
			 return ResponseEntity.ok(new MessageResponse("Deletado com sucesso")); 
	 	}
	 }
	 
	
	@DeleteMapping("/foto")
	public ResponseEntity<?> deleteFoto(@RequestBody Foto foto, Album album, HttpServletRequest request) {
		
		 foto = fotoService.findById(foto.getId());
		 String remoteUser = request.getRemoteUser();
		 
		 if(!remoteUser.equals(album.getAutor())) {
			 return ResponseEntity.badRequest()
						.body(new MessageResponse("Somente o autor tem autorização para exclusão"));
		 }else {	
			fotoService.delete(foto);
			return ResponseEntity.ok(new MessageResponse("Deletado com sucesso"));
		 }
	}

	
	@PutMapping("/album")
	public Album updateAlbum(@RequestBody Album album, String id) {	
		return albumService.save(album);
	}
	
}
