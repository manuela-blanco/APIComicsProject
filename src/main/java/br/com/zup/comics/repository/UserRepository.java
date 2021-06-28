package br.com.zup.comics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.comics.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findById(Long id);
	
	@Query(value = "SELECT * FROM user u WHERE u.cpf = ?1 or u.email = ?2", 
			  nativeQuery = true)
	public UserEntity findByCpfOrEmail(String cpf, String email);

}
