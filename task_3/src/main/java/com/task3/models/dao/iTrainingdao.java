package com.task3.models.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.task3.models.entity.Training;

import jakarta.annotation.PostConstruct;

public interface iTrainingdao extends CrudRepository<Training, Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@PostConstruct
	public default void init() {
		logger.info("itrainingedao iniciado");
	}
}
