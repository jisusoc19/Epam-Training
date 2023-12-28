package com.task3.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Training  implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(Training.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private Trainee trainee;


	private Trainer trainer;


	private String trainingName;


	private Training_Type training_type;


	private Date training_date;

	private Long training_duration;


	@PrePersist
	public void prePersist() {
		training_date = new Date();
	}
	private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Trainee getTrainee() {
		return trainee;
	}
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public Training_Type getTraining_type() {
		return training_type;
	}
	public void setTraining_type(Training_Type training_type) {
		this.training_type = training_type;
	}
	public Date getTraining_date() {
		return training_date;
	}
	public void setTraining_date(Date training_date) {
		this.training_date = training_date;
	}
	public Long getTraining_duration() {
		return training_duration;
	}
	public void setTraining_duration(Long training_duration) {
		this.training_duration = training_duration;
	}
	
	public void init() {
		logger.info("Training Entity Creado");
	}


}
