package com.task3.models.service.trainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.Repository.iTrainerdao;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Trainer;
import com.task3.models.entity.User;

import jakarta.annotation.PostConstruct;

@Service
public class iTrainerServiceImpl implements iTrainerService {
	private static final Logger logger = LoggerFactory.getLogger(iTrainerServiceImpl.class);
	
	@Autowired
	public iTrainerdao itrainerdaoJPA;
	
	@Transactional
	@Override
	public Trainer save(Trainer trainer) {
		try {
			itrainerdaoJPA.save(trainer);
			logger.info("trainer guardado con exito");
		} catch (Exception e) {
			logger.info("Error al guardar" +e.getMessage());
		}
		return trainer;
	}
	@Transactional(readOnly = true)
	@Override
	public Trainer findById(Long id) {
		if(itrainerdaoJPA.findById(id)==null) {
			logger.error("Trainer encontrado con id: " + id + "No encontrado");
			return null;
		}else {
			logger.info("Trainer encontrado con id: " + id + " Encontrado " );
			return itrainerdaoJPA.findById(id).orElse(null);
		}
		
	}
	@Transactional
	@Override
	public Trainer update(Trainer trainer, Long id) {
		Optional<Trainer> Aactual = itrainerdaoJPA.findById(id);
		Trainer nuevotra=null;
		Trainer existingTrainer = Aactual.get();
	    if (itrainerdaoJPA.findById(id)==null) {
	        // Update the trainer
	    	logger.info("Trainer with ID " + id + " update in the database");
	        return trainer;
	    }
	    try {
	    	existingTrainer.setTrainertype(trainer.getTrainertype());
		} catch (DataAccessException e) {
			logger.error("Error en Trainer updateSerice " + e.getMessage());
		}
	    return nuevotra = itrainerdaoJPA.save(existingTrainer);
	}
	@PostConstruct
	public void init() {
		logger.info("Iniciando el iTrainerServiceImpl");
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trainer> findAll() {
		// TODO Auto-generated method stub
		return (List<Trainer>) itrainerdaoJPA.findAll();
	}

}
