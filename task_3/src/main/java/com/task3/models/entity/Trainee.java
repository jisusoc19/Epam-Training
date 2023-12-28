package com.task3.models.entity;

import java.io.Serializable;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class Trainee implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Trainee.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dateBirth;


	private String addres;


	private User userid;

	@PrePersist
	public void prePersist() {
		dateBirth = new Date();
	}


	public Trainee(String addres, User userid) {
		this.addres = addres;
		this.userid = userid;
	}


	public Trainee() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDateBirth() {
		return dateBirth;
	}


	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}


	public String getAddres() {
		return addres;
	}


	public void setAddres(String addres) {
		this.addres = addres;
	}


	public User getUserid() {
		return userid;
	}


	public void setUserid(User userid) {
		this.userid = userid;
	}


	private static final long serialVersionUID = 1L;
	@PostConstruct
	public void init() {
		logger.info("Trainee Entity Creado");
	}


}
