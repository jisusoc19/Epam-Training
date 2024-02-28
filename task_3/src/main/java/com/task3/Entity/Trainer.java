package com.task3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Slf4j
@Table(name = "trainer")
public class Trainer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private User userid;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization")
	private Training_Type trainertype;
				
	@OneToMany(mappedBy = "trainer_id")
	private List<Training> traininglist;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "pasword", unique = true)
	private String password;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "isActive", nullable = false)
	private Boolean isActive;
	
	
	private static final long serialVersionUID = 1L;

	public void init() {
		log.info("Trainer Entity Creado");
	}
	public Trainer (User userid) {
		this.userid=userid;
		
	}



}
