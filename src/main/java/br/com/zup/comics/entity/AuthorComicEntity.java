package br.com.zup.comics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_comic")
public class AuthorComicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "author_id")
	private Long authorId;
	@Column(name = "comic_id")
	private Long comicId;
	
	public Long getAuthorId() {
		return authorId;
	}
	
	public Long getComicId() {
		return comicId;
	}
	
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	public void setComicId(Long comicId) {
		this.comicId = comicId;
	}
}
