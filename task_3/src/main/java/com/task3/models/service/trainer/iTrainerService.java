package com.task3.models.service.trainer;


import java.util.List;

import org.springframework.stereotype.Service;

import com.task3.models.entity.Trainer;
import com.task3.models.entity.User;

@Service
public interface iTrainerService {
	public Trainer save(Trainer trainer);
	public Trainer findById(Long id);
	public Trainer update(Trainer trainer , Long id);
	public List<Trainer> findAll();
}
