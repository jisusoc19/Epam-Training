package com.task3.models.service.trainee;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task3.models.entity.Trainee;
import com.task3.models.entity.User;

@Service
public interface iTraineeService {
	public List<Trainee> findAll();
	public Trainee  findById(Long id);
	Trainee save(Trainee trainee);
	public void delete(Long id);
	public Trainee update(Trainee traine, Long id);
	public Trainee findbyusername(String username);
	String  generateUniqueUsername(String firstName, String lastName);
	String  generatePasword(String password);
	Boolean loggin(String username, String Password );
	Trainee logginUpdate(String username, String Password,String Passwordold );
	void deleteByusername(String username);
	Trainee updateStatususername(String username, Boolean status);
}
