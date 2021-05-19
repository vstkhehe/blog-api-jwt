package com.blog.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.teste.model.Usuario;
import com.blog.teste.repository.UsuarioRepository;

@Service
public class UsuarioDetalhesServiceImpl implements UserDetailsService{
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException{
		Usuario user = usuarioRepository.findByUsuario(usuario);
			
		return UsuarioDetalhesImpl.build(user);
	}
}
