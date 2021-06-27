package br.com.zup.comics.model;

public class Author {

	private String nome;
	
	public Author() {}
	public Author(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
