package com.task3.models.Repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task3.models.entity.Trainee;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTraineedao extends CrudRepository<Trainee,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	@PostConstruct
	public default void init() {
		logger.info("itraineedao iniciado");
	}

}
