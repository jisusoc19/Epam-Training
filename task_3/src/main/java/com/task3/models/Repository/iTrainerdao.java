package com.task3.models.Repository;

import java.util.Optional;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.entity.Trainer;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainerdao extends JpaRepository<Trainer,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	//Trainer findByUsername(String username);
	@Transactional
	@Query("SELECT t FROM Trainer t WHERE t.username = :username")
	Optional<Trainer> findbyusername(String username);
	
	@Transactional
	@Query("SELECT t FROM Trainer t WHERE t.username = :username AND t.password = :password")
	Optional<Trainer> loggin(String username, String password );
	
	@PostConstruct
	public default void init() {
		logger.info("itrainerdao iniciado");
	}
}
