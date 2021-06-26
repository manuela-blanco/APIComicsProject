package br.com.zup.comics.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	private String nome;
	private String email;
	private String cpf;
	private Date dataNascimento;
	private List<Comic> registeredComics;
	
	public User() {
		this.registeredComics = new ArrayList<>();
	}
	
	public User(String nome, String email, String cpf, Date dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.registeredComics = new ArrayList<>();
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
    
    public List<Comic> getRegisteredComics() {
		return registeredComics;
	}
    
    public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    
    public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public void setNome(String name) {
		this.nome = name;
	}
    
    public void setRegisteredComics(List<Comic> registeredComics) {
		this.registeredComics = registeredComics;
	}
    
    public void addComic(Comic book) {
    	this.registeredComics.add(book);
    }
    
}
