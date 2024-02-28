package com.task3.service.trainer;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.User;

@Service
public interface iTrainerService {
	public Trainer save(Trainer trainer);
	public Trainer findById(Long id);
	public Trainer update(Trainer trainer , Long id);
	public List<Trainer> findAll();
	public Trainer findbyusername(String username);
	String generateUniqueUsername(String firstName, String lastName);
	String generatePasword(String password);
	Boolean loggin(String username, String Password );
	Trainer logginUpdate(String username, String Password,String Passwordold );
	Trainer activeTranierTrainee(String username, Boolean status);
}
