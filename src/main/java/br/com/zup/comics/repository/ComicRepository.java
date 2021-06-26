package br.com.zup.comics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.comics.entity.ComicEntity;

public interface ComicRepository extends JpaRepository<ComicEntity, Long> {
	
	public Optional<ComicEntity> findById(Long id);
	
	@Query(value = "SELECT * FROM Comic c INNER JOIN user_comic uc on (uc.user_id = ?1 and c.id = comic_id)", 
			  nativeQuery = true)
	public List<ComicEntity> findAllByUserId(Long userId);
}
