package com.task3.models.service.trainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task3.models.dao.iTrainerdao;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Trainer;

import jakarta.annotation.PostConstruct;

@Service
public class iTrainerServiceImpl implements iTrainerService {
	Map<Long,Trainer> trainerDao;
	private static final Logger logger = LoggerFactory.getLogger(iTrainerServiceImpl.class);
	
	@Autowired
	public iTrainerdao itrainerdaoJPA;
	
	public iTrainerServiceImpl() {
		this.trainerDao = new HashMap<>();
	}
	
	@Override
	public Trainer save(Trainer trainer) {
		try {
			trainerDao.put(trainer.getId(), trainer);
			logger.info("trainer guardado con exito");
		} catch (Exception e) {
			logger.info("Error al guardar" +e.getMessage());
		}
		return trainer;
	}

	@Override
	public Trainer findById(Long id) {
		Trainer trainer = trainerDao.get(id);
		if(trainer==null) {
			logger.info("Trainer encontrado con id: " + id);
		}else {
			logger.error("el trainer con el id " + id + " No encontrado ");
		}
		return trainer;
	}

	@Override
	public Trainer update(Trainer trainer, Long id) {
	    // Check if the trainer exists in the map
	    if (trainerDao.containsKey(id)) {
	        // Update the trainer
	        trainerDao.put(id, trainer);
	        return trainer;
	    } else {
	        // Log an error if the trainer is not found
	        logger.error("Trainer with ID " + id + " not found in the database");
	        return null; // or throw an exception
	    }
	}
	@PostConstruct
	public void init() {
		logger.info("Iniciando el iTrainerServiceImpl");
	}

}
