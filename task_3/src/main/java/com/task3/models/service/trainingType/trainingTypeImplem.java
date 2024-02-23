package com.task3.models.service.trainingType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.Repository.iTrainer_TypeRepo;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Training_Type;

import RestHandlerException.ResouceNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class trainingTypeImplem implements training_typeService {

	@Autowired
	private iTrainer_TypeRepo trainer_typerepo;
	
	@Transactional
	@Override
	public List<Training_Type> findALL(){
		List<Training_Type> lista = (List<Training_Type>) trainer_typerepo.findAll();
		if(lista==null || lista.isEmpty()) {
			log.error("no se encontro ninguna lista de Training_type");
			throw new ResouceNotFoundException("Training_type");
		}
		log.info("lista de Training_type encontrada");
		return lista;
	}
	
	
	@PostConstruct
	public void init() {
		log.info("trainingTypeImplem iniciado");
	}


}
