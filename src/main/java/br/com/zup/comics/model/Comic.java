package br.com.zup.comics.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.comics.utils.Day;

public class Comic {
	private String titulo;
	private double preco;
	private boolean onlinePurchasedPrice;
	private List<Author> autores;
	private String isbn;
	private String descricao;
	private Day diaDesconto;
	private boolean descontoAtivo;
	private static final double PORCENTAGEM_DESCONTO = 0.1;
	
	public Comic() {
		this.autores = new ArrayList<>();
	}
	
	public Comic(String titulo, double preco, List<Author> autores, 
			String isbn, String descricao) {
		
		this.titulo = titulo;
		this.preco = preco;
		this.autores = autores;
		this.isbn = isbn;
		this.descricao = descricao;
		
	}
	
	public void calculoDiaDesconto() {
		char digito = isbn.charAt(isbn.length() - 1);
		
		switch(digito) {
		case '0':
		case '1':
			this.diaDesconto = Day.SEGUNDA_FEIRA;
			break;
		case '2':
		case '3':
			this.diaDesconto = Day.TERCA_FEIRA;
			break;
		case '4':
		case '5':
			this.diaDesconto = Day.QUARTA_FEIRA;
			break;
		case '6':
		case '7':
			this.diaDesconto = Day.QUINTA_FEIRA;
			break;
		case '8':
		case '9':
			this.diaDesconto = Day.SEXTA_FEIRA;
			break;			
		}
	}
	
	public void calculoDescontoAtivo() {
		String currentDayOfTheWeek = LocalDate.now().getDayOfWeek().name();
		if(currentDayOfTheWeek.equals(this.diaDesconto.name())) {
			this.descontoAtivo = true;
		} 
		
		descontoAtivo = false;
	}
	
	public List<Author> getAutores() {
		return autores;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public Day getDiaDesconto() {
		return diaDesconto;
	}
	
	public boolean getDescontoAtivo() {
		return descontoAtivo;
	}
	
	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setDescontoAtivo(boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}
	
	public void setDiaDesconto(Day day) {
		this.diaDesconto = day;
	}
	
	public void addAuthor(Author author) {
		this.autores.add(author);
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("data")
	private void unpackNested(Map<String,Object> data) {
		ArrayList<Map<String, Object>> results = (ArrayList<Map<String, Object>>) data.get("results");
		Map<String, Object> firstResult = results.get(0);
		this.titulo = (String) firstResult.get("title");
		this.descricao = (String) firstResult.get("description");
		this.isbn = (String) firstResult.get("isbn");
		ArrayList<Map<String, Object>> prices = (ArrayList<Map<String, Object>>) firstResult.get("prices");
		verifyPrice(prices);
		Map<String, Object> creators = (Map<String, Object>) firstResult.get("creators");
		ArrayList<Map<String, Object>> allAuthors = (ArrayList<Map<String, Object>>) creators.get("items");
		unpackNestedAuthors(allAuthors);
	}
	
	private void unpackNestedAuthors(ArrayList<Map<String, Object>> allAuthors) {
		for(Map<String, Object> authorItem : allAuthors) {
			String authorName = (String) authorItem.get("name");
			Author author = new Author(authorName);
			this.addAuthor(author);
		}
	}
	
	private void verifyPrice(ArrayList<Map<String, Object>> prices) {
		Map<String, Object> printPrice = prices.get(0);
		String price = printPrice.get("price").toString();
		if(Double.valueOf(price) == 0.0 && prices.size() > 1) {
			this.preco = Double.valueOf(prices.get(1).get("price").toString());
			this.setOnlinePurchasedPrice(true);
			return;
		}
		
		this.preco = Double.valueOf(price);
		this.setOnlinePurchasedPrice(false);
	}

	public double precoComDesconto() {
		return preco * (1 - PORCENTAGEM_DESCONTO);
	}

	public boolean isOnlinePurchasedPrice() {
		return onlinePurchasedPrice;
	}

	public void setOnlinePurchasedPrice(boolean onlinePurchasedPrice) {
		this.onlinePurchasedPrice = onlinePurchasedPrice;
	}

}
