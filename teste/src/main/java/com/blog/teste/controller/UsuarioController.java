package com.blog.teste.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.teste.model.Usuario;
import com.blog.teste.payload.request.LoginRequest;
import com.blog.teste.payload.request.SignupRequest;
import com.blog.teste.payload.response.JwtResponse;
import com.blog.teste.payload.response.MessageResponse;
import com.blog.teste.security.jwt.JwtUtils;
import com.blog.teste.service.UsuarioDetalhesImpl;
import com.blog.teste.service.UsuarioService;



@RestController
@RequestMapping(value="/api/auth")
public class UsuarioController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping(value = "/usuarios")
	public Iterable<Usuario> getUsuarios() {
		return usuarioService.findAll();
	}
	
	@GetMapping(value = "/usuarios/{id}")
	public Optional<Usuario> getUsuariosById(@PathVariable(value="id") String id) {
		return usuarioService.findById(id);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getSenha()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UsuarioDetalhesImpl usuarioDetalhes = (UsuarioDetalhesImpl) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(jwt,
								usuarioDetalhes.getId(),
								usuarioDetalhes.getUsername()));
		
		
	}
	
	@PostMapping(value = "/signup")
	public ResponseEntity<?> saveUsuario(@Valid @RequestBody SignupRequest signUpRequest){	
		if(usuarioService.existsByUsuario(signUpRequest.getUsuario())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Usuario já existe"));
		}
		
		//Criar novo usuario
		Usuario usuario = new Usuario(signUpRequest.getUsuario(),
						  encoder.encode(signUpRequest.getSenha()));
		
		usuarioService.save(usuario);
		
		return ResponseEntity.ok(new MessageResponse("Usuario registrado com sucesso"));
		
	}
	
}
