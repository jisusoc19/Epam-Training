package com.task3.models.service.training;



import org.springframework.stereotype.Service;

import com.task3.models.entity.Training;

@Service
public interface iTrainingService{
	public Training findbyid(Long id);
	public Training save(Training training , Long id);
}
