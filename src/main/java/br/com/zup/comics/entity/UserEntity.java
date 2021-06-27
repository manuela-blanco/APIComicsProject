package br.com.zup.comics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String cpf;
	@Column(name = "data_nascimento")
	private Date dataNascimento;
		
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

}
