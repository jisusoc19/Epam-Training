package com.task3.models.entity;
import java.io.Serializable;



import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Trainee")
public class Trainee implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Trainee.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateBirth", nullable = false)
	private Date dateBirth;

	@Column(name="addres", length = 20 , nullable = false)
	private String addres;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private User userid;
	
	@OneToMany(mappedBy = "trainee_id")
	List<Training> traininglist;
	
	@PrePersist
	public void prePersist() {
		dateBirth = new Date();
	}


	public Trainee(String addres, User userid) {
		this.addres = addres;
		this.userid = userid;
	}



	private static final long serialVersionUID = 1L;
	@PostConstruct
	public void init() {
		logger.info("Trainee Entity Creado");
	}


}
