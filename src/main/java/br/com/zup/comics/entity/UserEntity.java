package br.com.zup.comics.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String cpf;
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	@ManyToMany
	private List<ComicEntity> registeredComics = new ArrayList<>();
		
	public Long getId() {
		return id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ComicEntity> getRegisteredComics() {
		return registeredComics;
	}

	public void setRegisteredComics(List<ComicEntity> list) {
		this.registeredComics = list;
	}
}
