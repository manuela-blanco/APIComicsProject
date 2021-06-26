package br.com.zup.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.comics.entity.UserComicEntity;

public interface UserComicRepository  extends JpaRepository<UserComicEntity, Long>{

}
