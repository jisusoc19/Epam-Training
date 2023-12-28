package com.task3.models.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.task3.models.entity.Trainee;

import jakarta.annotation.PostConstruct;

public interface iTraineedao extends CrudRepository<Trainee,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	
	@PostConstruct
	public default void init() {
		logger.info("itraineedao iniciado");
	}

}
