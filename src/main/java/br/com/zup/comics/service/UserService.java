package br.com.zup.comics.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.comics.entity.AuthorEntity;
import br.com.zup.comics.entity.ComicEntity;
import br.com.zup.comics.entity.UserComicEntity;
import br.com.zup.comics.entity.UserEntity;
import br.com.zup.comics.model.Author;
import br.com.zup.comics.model.Comic;
import br.com.zup.comics.model.User;
import br.com.zup.comics.repository.AuthorRepository;
import br.com.zup.comics.repository.ComicRepository;
import br.com.zup.comics.repository.UserComicRepository;
import br.com.zup.comics.repository.UserRepository;
import br.com.zup.comics.utils.ConverterService;

@Service
public class UserService {
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComicRepository userComicRepository;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ConverterService converter;
	
	public UserEntity findById(Long userId) {
		Optional<UserEntity> userFound = this.userRepository.findById(userId);
		UserEntity user = userFound.orElse(null);
		return user;
	}
	
	public UserEntity create(User user) {
		if(Objects.nonNull(user)) {
			UserEntity userEntity = this.converter.convert(user, UserEntity.class);
			userRepository.save(userEntity);
			return userEntity;
		}
		
		return null;
	}

	public UserComicEntity associate(UserEntity userEntity, ComicEntity comicEntity) {
		UserComicEntity uceFound = this.userComicRepository.findByIds(userEntity.getId(), comicEntity.getId());
		if(Objects.isNull(uceFound)) {
			UserComicEntity ucEntity = new UserComicEntity();
			ucEntity.setComicId(comicEntity.getId());
			ucEntity.setUserId(userEntity.getId());
			this.userComicRepository.save(ucEntity);		
			return ucEntity;
		}
		return uceFound;
	}

	public User findComicsAndAuthors(UserEntity userEntity) {
		List<ComicEntity> comics = this.comicRepository.findAllByUserId(userEntity.getId());
		User user = this.converter.convert(userEntity, User.class);
		for(ComicEntity comicEntity : comics) {
			List<AuthorEntity> autoresEntity = this.authorRepository.findAllByComicId(comicEntity.getId());
			List<Author> autores = this.converter.convertList(autoresEntity, Author.class);
			Comic comic = this.converter.convert(comicEntity, Comic.class);
			comic.setAutores(autores);
			this.comicService.verifyDesconto(comic);
			user.getRegisteredComics().add(comic);	
		}
	
		return user;
	}

}
