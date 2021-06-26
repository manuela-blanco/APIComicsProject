package br.com.zup.comics.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.comics.entity.ComicEntity;
import br.com.zup.comics.entity.UserComicEntity;
import br.com.zup.comics.entity.UserEntity;
import br.com.zup.comics.model.User;
import br.com.zup.comics.repository.UserComicRepository;
import br.com.zup.comics.repository.UserRepository;
import br.com.zup.comics.utils.ConverterService;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComicRepository userComicRepository;
	
	@Autowired
	private ComicService comicService;
	
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
		UserComicEntity ucEntity = new UserComicEntity();
		ucEntity.setComicId(comicEntity.getId());
		ucEntity.setUserId(userEntity.getId());
		this.userComicRepository.save(ucEntity);
		return ucEntity;
	}
	
	public void findRegisteredComics(UserEntity userEntity) {
		userEntity.setRegisteredComics(comicService.listAll(userEntity.getId()));
	}

}
