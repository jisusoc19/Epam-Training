package com.task3.models.service.trainingType;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task3.models.Repository.iTrainer_TypeRepo;
import com.task3.models.entity.Training_Type;

@Service
public interface training_typeService{
	List<Training_Type> findALL();
	
}
