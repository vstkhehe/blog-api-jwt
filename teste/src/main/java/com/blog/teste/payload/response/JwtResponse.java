package com.blog.teste.payload.response;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String id;
	private String usuario;
	
	public JwtResponse(String accessToken, String id, String usuario) {
		this.token = accessToken;
		this.id = id;
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
