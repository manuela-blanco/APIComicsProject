package br.com.zup.comics.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.comics.entity.UserComicEntity;
import br.com.zup.comics.entity.UserEntity;
import br.com.zup.comics.model.User;
import br.com.zup.comics.service.ComicService;
import br.com.zup.comics.service.UserService;
import br.com.zup.comics.utils.FormatDataNascimento;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private FormatDataNascimento formatador;

	@PostMapping("/register")
	public ResponseEntity<?> create(@RequestBody(required = true) Map<String, String> userData) throws ParseException {
		if(this.userService.verifyData(userData)) {
			UserEntity userEntity = this.userService.create(new User(userData.get("name"), 
					userData.get("email"), userData.get("cpf"), formatador.formata(userData.get("dataNascimento"))));
			return userEntity != null ? 
					ResponseEntity.status(HttpStatus.CREATED).body(userEntity) : 
						ResponseEntity.badRequest().body("Este cpf/e-mail já está associado a uma conta.");
		}

		return ResponseEntity.badRequest().body("Por favor informar todos os dados do usuário.");
	}
	
	@PostMapping("/associate/{userId}/comic/{apiComicId}")
	public ResponseEntity<UserComicEntity> associate(@PathVariable Long userId, @PathVariable Long apiComicId) {
		
		UserComicEntity userComicEntity = this.userService.associate(this.userService.findById(userId), 
				this.comicService.fetchDataFromMarvel(apiComicId));
		return userComicEntity != null ? ResponseEntity.status(HttpStatus.CREATED).body(userComicEntity) : ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{userId}/listComics")
	public ResponseEntity<?> listComics(@PathVariable Long userId) {
		User user = this.userService.findComicsAndAuthors(this.userService.findById(userId));
		return user != null ? 
				ResponseEntity.status(HttpStatus.OK).body(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	}
}
