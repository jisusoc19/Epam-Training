package com.task3.models.service.trainee;


import java.util.List;

import com.task3.models.entity.Trainee;

public interface iTraineeService {
	public List<Trainee> findAll();
	public Trainee  findById(Long id);
	Trainee save(Trainee trainee);
	public void delete(Long id);
	public Trainee update(Trainee traine, Long id);
	

}
