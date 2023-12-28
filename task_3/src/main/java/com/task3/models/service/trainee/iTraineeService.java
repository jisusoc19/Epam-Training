package com.task3.models.service.trainee;


import com.task3.models.entity.Trainee;

public interface iTraineeService {
	public void findAll();
	public Trainee  findById(Long id);
	Trainee save(Trainee trainee);
	public void delete(Long id);
	public Trainee update(Trainee traine, Long id);
	

}
