package com.task3.models.service.trainer;


import org.springframework.stereotype.Service;

import com.task3.models.entity.Trainer;

@Service
public interface iTrainerService {
	public Trainer save(Trainer trainer);
	public Trainer findById(Long id);
	public Trainer update(Trainer trainer , Long id);

}
