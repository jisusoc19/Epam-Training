package com.task3.models.Repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.entity.User;

import jakarta.annotation.PostConstruct;
@Repository
public interface iUserRepository extends CrudRepository<User, Long>{
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@Transactional
	@Query("SELECT t FROM User t WHERE t.username=:username")
	Optional<User> findByUsername(String username);
	@PostConstruct
	public default void init() {
		logger.info("iUserRepo iniciado");
	}
}
