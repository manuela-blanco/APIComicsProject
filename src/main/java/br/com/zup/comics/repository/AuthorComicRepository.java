package br.com.zup.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.comics.entity.AuthorComicEntity;

public interface AuthorComicRepository extends JpaRepository<AuthorComicEntity, Long>{

}
