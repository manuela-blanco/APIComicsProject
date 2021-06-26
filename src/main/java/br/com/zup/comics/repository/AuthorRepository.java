package br.com.zup.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.comics.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
