package br.com.zup.comics.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zup.comics.entity.ComicEntity;
import br.com.zup.comics.model.Comic;
import br.com.zup.comics.repository.ComicRepository;
import br.com.zup.comics.utils.ConverterService;

@Service
public class ComicService {
	
	private final static String BASE_URL = "http://gateway.marvel.com/v1/public/comics";
	
	@Value("${marvel.api.public.key}")
	private String publicKey;
	
	@Value("${marvel.api.private.key}")
	private String privateKey;
	
	@Value("${marvel.api.timestamp}")
	private String timestamp;

	@Autowired
    public RestTemplate restTemplate;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ConverterService converter;
	
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
		String url = BASE_URL + "/" + comicId + "?ts=" + timestamp + "&apikey=" + publicKey
				+ "&hash=" + hash;
		ResponseEntity<Comic> comicResponse = restTemplate.getForEntity(url, Comic.class);
		Comic comic = comicResponse.getBody();
		ComicEntity comicFound = this.comicRepository.findByTitulo(comic.getTitulo());
		if(Objects.isNull(comicFound)) {
			this.verifyDesconto(comic);
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

	public String hashCredentials(String credentials) {
		return DigestUtils.md5Hex(credentials);
	}

	public List<ComicEntity> listAll(Long userId) {
		return this.comicRepository.findAllByUserId(userId);		
	}

}
