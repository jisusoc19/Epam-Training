package com.task3.models.entity;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Trainer implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(Trainer.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private User userid;


	private Training_Type trainertype;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	public Training_Type getTrainertype() {
		return trainertype;
	}

	public void setTrainertype(Training_Type trainertype) {
		this.trainertype = trainertype;
	}
	public void init() {
		logger.info("Trainer Entity Creado");
	}



}
