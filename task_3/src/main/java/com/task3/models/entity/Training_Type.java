package com.task3.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor  
@Entity
@Data
@Table(name = "training_type")
public class Training_Type implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Training_Type.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String trainingTypeName;
	
	
	@OneToMany(mappedBy = "trainertype")
	List<Trainer> trainerone;
	
	@OneToMany(mappedBy = "training_type_id")
	List<Training> traininglist;
	
	private static final long serialVersionUID = 1L;
	public void init() {
		logger.info("Training_type Entity Creado");
	}

}
