package com.task3.models.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import com.task3.models.entity.Training_Type;

import jakarta.annotation.PostConstruct;

public interface iTrainer_TypeRepo extends CrudRepository<Training_Type,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@PostConstruct
	public default void init() {
		logger.info("itraier_type iniciado");
	}
}
