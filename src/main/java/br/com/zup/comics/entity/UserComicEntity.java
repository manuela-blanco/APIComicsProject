package br.com.zup.comics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_comic")
public class UserComicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "comic_id")
	private Long comicId;
	
	public Long getComicId() {
		return comicId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setComicId(Long comicId) {
		this.comicId = comicId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
