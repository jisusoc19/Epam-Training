package com.task3.service.training;



import org.springframework.stereotype.Service;

import com.task3.Entity.Training;

@Service
public interface iTrainingService{
	public Training findbyid(Long id);
	public Training save(Training training , Long id);
}
