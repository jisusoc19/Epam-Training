package com.task3.service.trainingType;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task3.Entity.Training_Type;
import com.task3.Repository.iTrainer_TypeRepo;

@Service
public interface training_typeService{
	List<Training_Type> findALL();
	
}
