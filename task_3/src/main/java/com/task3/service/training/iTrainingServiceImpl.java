package com.task3.service.training;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Entity.Training;
import com.task3.Repository.iTrainingdao;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class iTrainingServiceImpl implements iTrainingService {
	

	@Autowired
	private iTrainingdao itrainindaoJPA;
	
	@Transactional(readOnly = true)
	@Override
	public Training findbyid(Long id) {
		// TODO Auto-generated method stub
		if(itrainindaoJPA.existsById(id)) {
			log.info("Training con el id " + id + " Seleccionado");
			
			return itrainindaoJPA.findById(id).orElseGet(null);
		}else {
			log.error("Training con el id " + id + " no encontrado");
			return null;
		}
		
	}
	@Transactional(readOnly = false)
	@Override
	public Training save(Training training, Long id) {
		if(itrainindaoJPA.existsById(id)) {
			itrainindaoJPA.save(training);
			log.info("Training guardado con exito");
		}else {
			log.error("Error al guardar el Training con el id :" + id);
			return null;
		}
		return training;
	}
	@PostConstruct
	public void init() {
		log.info("Iniciando el iTrainingServiceimpl");
	}

}
