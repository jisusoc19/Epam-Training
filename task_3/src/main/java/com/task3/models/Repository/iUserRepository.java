package com.task3.models.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import com.task3.models.entity.User;

import jakarta.annotation.PostConstruct;

public interface iUserRepository extends CrudRepository<User, Long>{
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@PostConstruct
	public default void init() {
		logger.info("iUserRepo iniciado");
	}
}
