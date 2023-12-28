package com.task3.models.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Training_Type implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Training_Type.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	private String trainingTypeName;




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrainingTypeName() {
		return trainingTypeName;
	}

	public void setTrainingTypeName(String trainingTypeName) {
		this.trainingTypeName = trainingTypeName;
	}

	private static final long serialVersionUID = 1L;
	public void init() {
		logger.info("Training_type Entity Creado");
	}

}
