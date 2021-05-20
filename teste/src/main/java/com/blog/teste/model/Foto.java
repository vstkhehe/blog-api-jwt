package com.blog.teste.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="FOTO")
public class Foto {
	
	public Foto() {
	}
	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	private String imagemNome;
	
	private String imagemTipo;
	
	private String imagemTamanho;
	
	private String album;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getImagemNome() {
		return imagemNome;
	}

	public void setImagemNome(String imagemNome) {
		this.imagemNome = imagemNome;
	}

	public String getImagemTipo() {
		return imagemTipo;
	}

	public void setImagemTipo(String imagemTipo) {
		this.imagemTipo = imagemTipo;
	}

	public String getImagemTamanho() {
		return imagemTamanho;
	}

	public void setImagemTamanho(String imagemTamanho) {
		this.imagemTamanho = imagemTamanho;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
}
