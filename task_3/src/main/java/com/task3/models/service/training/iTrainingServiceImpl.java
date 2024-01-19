package com.task3.models.service.training;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.Repository.iTrainingdao;
import com.task3.models.entity.Training;

import jakarta.annotation.PostConstruct;
@Service
public class iTrainingServiceImpl implements iTrainingService {
	private static final Logger logger = LoggerFactory.getLogger(iTrainingServiceImpl.class);

	@Autowired
	private iTrainingdao itrainindaoJPA;
	
	@Transactional(readOnly = true)
	@Override
	public Training findbyid(Long id) {
		// TODO Auto-generated method stub
		if(itrainindaoJPA.existsById(id)) {
			logger.info("Training con el id " + id + " Seleccionado");
			
			return itrainindaoJPA.findById(id).orElseGet(null);
		}else {
			logger.error("Training con el id " + id + " no encontrado");
			return null;
		}
		
	}
	@Transactional
	@Override
	public Training save(Training training, Long id) {
		if(itrainindaoJPA.existsById(id)) {
			itrainindaoJPA.save(training);
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
