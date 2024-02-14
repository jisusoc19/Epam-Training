package com.task3.models.Repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task3.models.entity.Trainer;
import com.task3.models.entity.Training_Type;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainer_TypeRepo extends CrudRepository<Training_Type,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@PostConstruct
	public default void init() {
		logger.info("itraier_type iniciado");
	}
}
