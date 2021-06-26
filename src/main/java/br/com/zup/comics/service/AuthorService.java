package br.com.zup.comics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.comics.entity.AuthorComicEntity;
import br.com.zup.comics.entity.AuthorEntity;
import br.com.zup.comics.entity.ComicEntity;
import br.com.zup.comics.repository.AuthorComicRepository;
import br.com.zup.comics.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorComicRepository authorComicRepository;

	public void create(List<String> autores, ComicEntity comicEntity) {
		for(String autor : autores) {
			AuthorEntity authorEntity = new AuthorEntity(autor);
			authorRepository.save(authorEntity);
			AuthorComicEntity authorComicEntity = new AuthorComicEntity();
			authorComicEntity.setAuthorId(authorEntity.getId());
			authorComicEntity.setComicId(comicEntity.getId());
			authorComicRepository.save(authorComicEntity);
		}
	}
}
