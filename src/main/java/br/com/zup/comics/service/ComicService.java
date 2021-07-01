package br.com.zup.comics.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.zup.comics.entity.ComicEntity;
import br.com.zup.comics.feign.client.MarvelAPIClient;
import br.com.zup.comics.model.Comic;
import br.com.zup.comics.repository.ComicRepository;
import br.com.zup.comics.utils.ConverterService;

@Service
public class ComicService {
		
	@Value("${marvel.api.public.key}")
	private String publicKey;
	
	@Value("${marvel.api.private.key}")
	private String privateKey;
	
	@Value("${marvel.api.timestamp}")
	private String timestamp;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ConverterService converter;
	
	@Autowired
	private MarvelAPIClient marvelAPIClient;
	
	public ComicEntity create(Comic comic) {
		if(Objects.nonNull(comic)) {
			ComicEntity comicEntity = this.converter.convert(comic, ComicEntity.class);
			comicRepository.save(comicEntity);
			return comicEntity;
		}
		
		return null;
	}
	
	public ComicEntity fetchDataFromMarvel(Long comicId) {
		String hash = hashCredentials(timestamp + privateKey + publicKey);
		Comic comic = marvelAPIClient.getComicById(comicId, timestamp, publicKey, hash);
		ComicEntity comicFound = this.comicRepository.findByTitulo(comic.getTitulo());
		if(Objects.isNull(comicFound)) {
			ComicEntity comicEntity = this.create(comic);
			this.authorService.create(comic.getAutores(), comicEntity);
			return comicEntity;
		}
		return comicFound;
	}
	
	protected void verifyDesconto(Comic comic) {
		if(Objects.nonNull(comic)) {
			if(Strings.isNotBlank(comic.getIsbn())) {
				comic.calculoDiaDesconto();
				comic.calculoDescontoAtivo();
			}
		}
		
	}
	
	protected void checkPreco(Comic comic) {
		if(comic.getDescontoAtivo()) {
			comic.setPreco(comic.precoComDesconto());
			return;
		}
		
		ComicEntity comicEntity = this.comicRepository.findByTitulo(comic.getTitulo());
		
		if(comic.getPreco() != comicEntity.getPreco()) {
			comic.setPreco(comicEntity.getPreco());
		}
	}

	public String hashCredentials(String credentials) {
		return DigestUtils.md5Hex(credentials);
	}

	public List<ComicEntity> listAll(Long userId) {
		return this.comicRepository.findAllByUserId(userId);		
	}

}
