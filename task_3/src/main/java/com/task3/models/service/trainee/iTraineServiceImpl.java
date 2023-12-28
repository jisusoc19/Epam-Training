package com.task3.models.service.trainee;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task3.Task3Application;
import com.task3.models.dao.iTraineedao;
import com.task3.models.entity.Trainee;


import jakarta.annotation.PostConstruct;

@Repository
public class iTraineServiceImpl implements iTraineeService {
	private static final Logger logger = LoggerFactory.getLogger(Task3Application.class);
	private Map<Long,Trainee> traineemapDao;
	
	@Autowired
	private iTraineedao trainerdaoJpa;
	
	public iTraineServiceImpl() {
		this.traineemapDao = new HashMap<>();
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
	    for (Map.Entry<Long, Trainee> i : traineemapDao.entrySet()) {
	        logger.info("Clave: " + i.getKey() + ", Valor: " + i.getValue());
	    }
	}

	@Override
	public Trainee findById(Long id) {
	    Trainee trainee = traineemapDao.get(id);
	    if (trainee == null) {
	        logger.error("No se encontró el trainee con el id: " + id);
	        return null;
	    } else {
	    	logger.info("Trainee con el id: " + id + " encontrado con éxito.");
	        return trainee;
	    }
	}

	@Override
	public Trainee save(Trainee trainee) {
		try {
			traineemapDao.put(trainee.getId(), trainee);
			logger.info("Trainee guardado con exito");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en guardar" + e.getMessage());
			return null;
		}
		
		return trainee;
	}

	@Override
	public void delete(Long id) {
		try {
			traineemapDao.remove(id);
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

	@Override
	public Trainee update(Trainee trainee, Long id) {
	    // Check if the trainer exists in the map
	    if (traineemapDao.containsKey(id)) {
	        // Update the trainer
	    	traineemapDao.put(id, trainee);
	    	logger.info("Trainee with ID " + id + " update in the database");
	        return trainee;
	    } else {
	        // Log an error if the trainer is not found
	        logger.error("Trainee with ID " + id + " not found in the database");
	        return null; // or throw an exception
	    }
	}

}
