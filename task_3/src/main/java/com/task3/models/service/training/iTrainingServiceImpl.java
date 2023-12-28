package com.task3.models.service.training;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task3.models.dao.iTrainingdao;
import com.task3.models.entity.Training;

import jakarta.annotation.PostConstruct;
@Repository
public class iTrainingServiceImpl implements iTrainingService {
	Map<Long, Training> trainingDao;
	private static final Logger logger = LoggerFactory.getLogger(iTrainingServiceImpl.class);

	@Autowired
	private iTrainingdao itrainindaoJPA;

	@Override
	public Training findbyid(Long id) {
		// TODO Auto-generated method stub
		if(trainingDao.containsKey(id)) {
			logger.info("Training con el id " + id + " Seleccionado");
			return trainingDao.get(id);
		}else {
			logger.error("Training con el id " + id + " no encontrado");
			return null;
		}
		
	}

	@Override
	public Training save(Training training, Long id) {
		if(trainingDao.containsKey(id)) {
			trainingDao.put(id,training);
			logger.info("Training guardado con exito");
		}else {
			logger.error("Error al guardar con el id :" + id);
			return null;
		}
		return training;
	}
	@PostConstruct
	public void init() {
		logger.info("Iniciando el iTrainingServiceimpl");
	}

}
