package com.blog.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.teste.model.Usuario;

@Service
public class UsuarioDetalhesServiceImpl implements UserDetailsService{
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException{
		Usuario user = null;
		user = usuarioService.findByUsuario(usuario);
			
		return UsuarioDetalhesImpl.build(user);
	}
}
