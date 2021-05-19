package com.blog.teste.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.teste.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDetalhesImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String usuario;
	
	@JsonIgnore
	private String senha;
	
	
	public UsuarioDetalhesImpl(String id, String usuario, String senha) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
			}
	
	public static UsuarioDetalhesImpl build(Usuario usuario) {
		return new UsuarioDetalhesImpl(
				usuario.getId(),
				usuario.getUsuario(),
				usuario.getSenha());	
	}

	public String getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
				return true;
		if( o == null || getClass() != o.getClass())
				return false;
		UsuarioDetalhesImpl usuario = (UsuarioDetalhesImpl) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
