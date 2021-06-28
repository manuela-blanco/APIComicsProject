package br.com.zup.comics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.comics.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

	@Query(value = "SELECT * FROM Author a INNER JOIN author_comic ac on (ac.comic_id = ?1 and a.id = author_id)", 
			  nativeQuery = true)
	public List<AuthorEntity> findAllByComicId(Long comicId);
	
	public AuthorEntity findByNome(String nome);
}
