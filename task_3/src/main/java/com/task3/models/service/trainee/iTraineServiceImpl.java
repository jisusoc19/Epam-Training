package com.task3.models.service.trainee;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Task3Application;
import com.task3.models.Repository.iTraineedao;
import com.task3.models.entity.Trainee;


import jakarta.annotation.PostConstruct;

@Service
public class iTraineServiceImpl implements iTraineeService {
	private static final Logger logger = LoggerFactory.getLogger(Task3Application.class);
	
	@Autowired
	private iTraineedao trainerdaoJpa;
	
	@Transactional(readOnly = true)
	@Override
	public List<Trainee> findAll() {
		// TODO Auto-generated method stub
	        logger.info("Lista de Trainee Ejecutada");
	        return (List<Trainee>) trainerdaoJpa.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Trainee findById(Long id) {
	    if (trainerdaoJpa.findById(id) == null) {
	        logger.error("No se encontró el trainee con el id: " + id);
	        return trainerdaoJpa.findById(id).orElse(null);
	    } else {
	    	logger.info("Trainee con el id: " + id + " encontrado con éxito.");
	        return trainerdaoJpa.findById(id).orElse(null);
	    }
	}
	@Transactional
	@Override
	public Trainee save(Trainee trainee) {
		try {
			trainerdaoJpa.save(trainee);
			logger.info("Trainee guardado con exito");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en guardar" + e.getMessage());
			return null;
		}
		return trainee;
	}
	@Transactional
	@Override
	public void delete(Long id) {
		try {
			// try Delete service 
			trainerdaoJpa.deleteById(id);
			logger.info("trainee con el id :" + id + " eliminado con exito");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al borrar" + e.getMessage());
		}		
	}
	
    @PostConstruct
    public void init() {
    	logger.info("iTraineServiceImpl ha sido inicializado");
    }
    @Transactional
	@Override
	public Trainee update(Trainee trainee, Long id) {
		Optional<Trainee> Aactual = trainerdaoJpa.findById(id);
		Trainee nuevotra=null;
		Trainee existingTrainee = Aactual.get();
	    if (trainerdaoJpa.findById(id)==null) {
	        // Update the trainer
	    	logger.info("Trainee with ID " + id + " update in the database");
	        return trainee;
	    }
	    try {
	    	existingTrainee.setAddres(trainee.getAddres());
		} catch (DataAccessException e) {
			logger.error("Error en Trainee updateSerice " + e.getMessage());
		}
	    return nuevotra = trainerdaoJpa.save(existingTrainee);
	}

}
