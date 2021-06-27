package br.com.zup.comics.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Comic")
public class ComicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private double preco;
	private	String isbn;
	private String descricao;
	@Column(name = "dia_desconto")
	private String diaDesconto;
	@Column(name = "desconto_ativo")
	private boolean descontoAtivo;
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getDiaDesconto() {
		return diaDesconto;
	}
	
	public Long getId() {
		return id;
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
	
	public boolean getDescontoAtivo() {
		return descontoAtivo;
	}
	
	public void setDescontoAtivo(boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDiaDesconto(String diaDesconto) {
		this.diaDesconto = diaDesconto;
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

	
}
