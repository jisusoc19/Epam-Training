package com.task3.models.Repository;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task3.models.entity.Trainer;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainerdao extends JpaRepository<Trainer,Long> {
	Logger logger = LoggerFactory.getLogger(iTraineedao.class);
	//Trainer findByUsername(String username);
	@PostConstruct
	public default void init() {
		logger.info("itrainerdao iniciado");
	}
}
