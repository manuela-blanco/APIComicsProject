package br.com.zup.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.comics.entity.UserComicEntity;

public interface UserComicRepository  extends JpaRepository<UserComicEntity, Long>{

	@Query(value = "SELECT * FROM user_comic uc WHERE uc.user_id = ?1 and uc.comic_id = ?2", 
			  nativeQuery = true)
	public UserComicEntity findByIds(Long userId, Long comicId);
}
