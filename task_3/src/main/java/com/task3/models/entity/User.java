package com.task3.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
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


@AllArgsConstructor
@Entity
@Data
@Table(name = "usergym")
public class User implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean isActive;

	@OneToOne(mappedBy ="userid")
	Trainee trainee;
	
	@OneToOne(mappedBy ="userid")
	Trainer trainer;
	
/*    public User() {
        this.userName = generaten(); 
    }

    private String generaten() {
    	String nombrecompleto = firstName + lastName;
    	if(nombrecompleto.e)
    	
        return firstName + lastName;
    }
*/
	
	public Long getId() {
		return id;
	}
	private static final long serialVersionUID = 1L;
	public void init() {
		logger.info("User Entity Creado");
	}

}
